package spaceattack.game.buttons;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.IGameplayInput;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class AcceleratorTest {

    @Mock
    private IGameplayInput processor;

    @Mock
    private IProgressButton button;

    @Mock
    private IVector vector;

    @InjectMocks
    private Accelerator accelerator;

    private IVectorFactory factory;

    @Before
    public void setUp() {

        factory = ExtVectorFactory.INSTANCE;
        Factories.setVectorFactory(factory);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

        accelerator = new Accelerator();
        MockitoAnnotations.initMocks(this);

        doReturn(0f).when(button).getX();
        doReturn(0f).when(button).getY();
        doReturn(100f).when(button).getWidth();
        doReturn(100f).when(button).getHeight();
    }

    @Test
    public void whenNotTouchedVerticalAccIsZero() {

        doReturn(null).when(processor).getTouch();

        accelerator.act(0);
        assertEquals(0, accelerator.getVerticalAcceleration(), 0);
    }

    @Test
    public void whenTouchOutOfButtonVerticalAccIsZero() {

        doReturn(vector).when(processor).getTouch();
        doReturn(factory.create(200, 200)).when(button).screenToStageCoordinates(nullable(IVector.class));

        accelerator.act(0);
        assertEquals(0, accelerator.getVerticalAcceleration(), 0);
    }

    @Test
    public void whenTouchedVerticalAccIsPercentOfUpperButtonPart() {

        doReturn(vector).when(processor).getTouch();
        doReturn(factory.create(50, 70)).when(button).screenToStageCoordinates(nullable(IVector.class));

        accelerator.act(0);
        assertEquals(40, accelerator.getVerticalAcceleration(), 0);
    }

    @Test
    public void whenTouchedVerticalBrakeIsPercentOfLowerButtonPart() {

        doReturn(vector).when(processor).getTouch();
        doReturn(factory.create(50, 35)).when(button).screenToStageCoordinates(nullable(IVector.class));

        accelerator.act(0);
        assertEquals(-30, accelerator.getVerticalAcceleration(), 0);
    }

    @Test
    public void onButtonTouchItIsRemembered() {

        doReturn(vector).when(processor).getTouch();
        doReturn(factory.create(50, 70)).when(button).screenToStageCoordinates(nullable(IVector.class));

        accelerator.act(0);
        verify(button).keep();
    }

    @Test
    public void ifNoTouchButtonIsReleased() {

        doReturn(null).when(processor).getTouch();

        accelerator.act(0);
        verify(button).release();
    }

    @Test
    public void touchedLeftPartOfButton() {

        doReturn(vector).when(processor).getTouch();
        doReturn(factory.create(25, 70)).when(button).screenToStageCoordinates(nullable(IVector.class));

        accelerator.act(0);
        assertEquals(-50, accelerator.getHorizontalAcceleration(), 0);
    }

    @Test
    public void keepingOutOfButton() {

        doReturn(vector).when(processor).getTouch();
        doReturn(factory.create(140, 50)).when(button).screenToStageCoordinates(nullable(IVector.class));
        doReturn(true).when(button).wasNotReleased();

        accelerator.act(0);
        assertEquals(100, accelerator.getHorizontalAcceleration(), 0);
    }

    @Test
    public void cornerIsNormalized() {

        doReturn(vector).when(processor).getTouch();
        doReturn(factory.create(0, 0)).when(button).screenToStageCoordinates(nullable(IVector.class));

        accelerator.act(0);
        assertEquals(-71, accelerator.getHorizontalAcceleration(), 1);
    }

    @Test
    public void randomLongVectorsAreNormalized() {

        doReturn(vector).when(processor).getTouch();
        doReturn(factory.create(95, 85)).when(button).screenToStageCoordinates(nullable(IVector.class));

        accelerator.act(0);
        assertEquals(79, accelerator.getHorizontalAcceleration(), 1);
        assertEquals(61, accelerator.getVerticalAcceleration(), 1);
    }
}
