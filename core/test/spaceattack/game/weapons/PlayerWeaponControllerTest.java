package spaceattack.game.weapons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.vector.IVectorFactory;

public class PlayerWeaponControllerTest {

    @Mock
    private IShip ship;

    @Mock
    private IWeapon primaryWeapon;

    @Mock
    private IWeapon secondaryWeapon;

    @Mock
    private IFireButton secondaryButton;

    private PlayerWeaponController controller;

    private IVectorFactory factory;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        factory = ExtVectorFactory.INSTANCE;
        Factories.setVectorFactory(factory);

        controller = new PlayerWeaponController();
        controller.setShip(ship);
        controller.setPrimaryWeapon(primaryWeapon);
    }

    @Test
    public void placementOfLaserShot() {

        doReturn(0.7f).when(primaryWeapon).getWeaponsMovementFactor();
        doReturn(100f).when(ship).getX();
        doReturn(200f).when(ship).getY();
        doReturn(60f).when(ship).getHeight();

        assertEquals(factory.create(100, 242), controller.getPrimaryWeaponUsePlacement());
    }

    @Test
    public void settingSecondaryWeapon() {

        controller.setSecondaryFireButton(secondaryButton);
        controller.setSecondaryWeapon(primaryWeapon);
        controller.updateSecondaryWeapon(secondaryWeapon);

        verify(secondaryButton).setWeapon(secondaryWeapon);
    }

    @Test
    public void whenButtonPressedAndEnoughtEnergyContinuousFireIsTriggered() {

        controller.setSecondaryFireButton(secondaryButton);
        doReturn(true).when(secondaryButton).isPressed();
        doReturn(true).when(ship).takeEnergy(anyFloat());

        assertTrue(controller.isContinuousFireTriggered(100));
    }

    @Test
    public void whenNotPressedContinuousFireIsNotTriggered() {

        controller.setSecondaryFireButton(secondaryButton);
        doReturn(false).when(secondaryButton).isPressed();
        doReturn(true).when(ship).takeEnergy(anyFloat());

        assertFalse(controller.isContinuousFireTriggered(100));
    }

    @Test
    public void whenTouchedDownAndTouchedUpAndEnoughtEnergyContinuousFireIsNotTriggered() {

        controller.setSecondaryFireButton(secondaryButton);
        doReturn(true).when(secondaryButton).isPressed();
        doReturn(false).when(ship).takeEnergy(anyFloat());

        assertFalse(controller.isContinuousFireTriggered(100));
    }
}
