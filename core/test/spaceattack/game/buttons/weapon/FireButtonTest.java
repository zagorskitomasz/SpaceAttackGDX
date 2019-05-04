package spaceattack.game.buttons.weapon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.buttons.IImageButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.InputType;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.ships.pools.Pool;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.IWeapon;

public class FireButtonTest {

    @Mock
    private IImageButton extButton;

    @Mock
    private IWeapon weapon;

    @Mock
    private IPool energyPool;

    private FireButton fireButton;

    private IVectorFactory factory;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        factory = ExtVectorFactory.INSTANCE;
        Factories.setVectorFactory(factory);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

        doReturn(80f).when(extButton).getWidth();
        doReturn(80f).when(extButton).getHeight();

        fireButton = new FireButton();
        fireButton.setImageButton(extButton);
        fireButton.setWeapon(weapon);
        fireButton.setPosition(160, 160);
        fireButton.setEnergyPool(energyPool);
    }

    @Test
    public void touchingCenterOfButtonIsFiringWeapon() {

        doReturn(factory.create(200, 200)).when(extButton).screenToStageCoordinates(any(IVector.class));

        boolean result = fireButton.touchUp(200, 200);

        verify(weapon).use();
        assertTrue(result);
    }

    @Test
    public void touchingBorderOfButtonIsFiringWeapon() {

        doReturn(factory.create(239, 200)).when(extButton).screenToStageCoordinates(any(IVector.class));

        boolean result = fireButton.touchUp(239, 200);

        verify(weapon).use();
        assertTrue(result);
    }

    @Test
    public void touchingOutOfBorderOfButtonIsNotFiringWeapon() {

        doReturn(factory.create(241, 200)).when(extButton).screenToStageCoordinates(any(IVector.class));
        boolean result = fireButton.touchUp(241, 200);

        verify(weapon, times(0)).use();
        assertFalse(result);
    }

    @Test
    public void cornerIsExcluded() {

        doReturn(factory.create(238, 238)).when(extButton).screenToStageCoordinates(any(IVector.class));
        boolean result = fireButton.touchUp(238, 238);

        verify(weapon, times(0)).use();
        assertFalse(result);
    }

    @Test
    public void releasingOutsideButtonIsUnpressing() {

        doReturn(factory.create(238, 238)).when(extButton).screenToStageCoordinates(any(IVector.class));
        doReturn(true).when(extButton).isPressed();
        boolean result = fireButton.touchUp(238, 238);

        verify(extButton).fire(any(InputType.class));
        assertFalse(result);
    }

    @Test
    public void disabledWhenNotifiedAndNotEnoughEnergy() {

        doReturn(100f).when(weapon).getEnergyCost();
        IPool pool = new Pool(10, 10, 10, 10);
        fireButton.setEnergyPool(pool);
        pool.update();

        verify(extButton).setEnabled(false);
    }

    @Test
    public void whenContinuousFireTouchingUpIsNotFiring() {

        doReturn(true).when(weapon).isContinuousFire();
        doReturn(factory.create(200, 200)).when(extButton).screenToStageCoordinates(any(IVector.class));

        fireButton.touchUp(200, 200);

        verify(weapon, never()).use();
    }

    @Test
    public void whenContinuousFireTouchingDownIsFiring() {

        doReturn(true).when(weapon).isContinuousFire();
        doReturn(factory.create(200, 200)).when(extButton).screenToStageCoordinates(any(IVector.class));

        fireButton.touchDown(200, 200);

        verify(weapon).use();
    }

    @Test
    public void whenTouchedDownIsPressed() {

        doReturn(factory.create(200, 200)).when(extButton).screenToStageCoordinates(any(IVector.class));

        fireButton.touchDown(200, 200);

        assertTrue(fireButton.isPressed());
    }

    @Test
    public void whenTouchedDownAndTouchedUpIsNotPressed() {

        doReturn(factory.create(200, 200)).when(extButton).screenToStageCoordinates(any(IVector.class));

        fireButton.touchDown(200, 200);
        fireButton.touchUp(200, 200);

        assertFalse(fireButton.isPressed());
    }

    @Test
    public void whenTouchedDownAndTouchedUpOutsideButtonIsStillPressed() {

        doReturn(factory.create(200, 200), factory.create(800, 800)).when(extButton)
                .screenToStageCoordinates(any(IVector.class));

        fireButton.touchDown(200, 200);
        fireButton.touchUp(800, 800);

        assertTrue(fireButton.isPressed());
    }
}
