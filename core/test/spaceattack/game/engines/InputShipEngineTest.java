package spaceattack.game.engines;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doReturn;

import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.consts.Sizes;
import spaceattack.game.buttons.IAccelerator;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.IShip;

public class InputShipEngineTest {

    private InputShipEngine engine;

    @Mock
    private IAccelerator accelerator;

    @Mock
    private Predicate<Float> energyFunction;

    @Mock
    private Supplier<Float> additionalMultiplier;

    private IShip ship;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        ship = new FakeShip();
        engine = new InputShipEngine(ship, 10, null, 0, additionalMultiplier);
        engine.setAccelerator(accelerator);
    }

    @Test
    public void shipIsMovedForward() {

        ship.setY(400);
        doReturn(100f).when(accelerator).getVerticalAcceleration();

        engine.fly();

        assertEquals(401, ship.getY(), 0);

        engine.fly();
        engine.fly();

        assertEquals(406, ship.getY(), 0);
    }

    @Test
    public void shipIsAcceleratedBy100Percent() {

        ship.setY(400);
        doReturn(100f).when(accelerator).getVerticalAcceleration();

        for (int i = 0; i < 15; i++) {
            engine.fly();
        }

        assertEquals(10, engine.currentSpeedVertical, 0);
    }

    @Test
    public void shipIsAcceleratedBy50Percent() {

        ship.setY(400);
        doReturn(50f).when(accelerator).getVerticalAcceleration();

        for (int i = 0; i < 15; i++) {
            engine.fly();
        }

        assertEquals(5, engine.currentSpeedVertical, 0);
    }

    @Test
    public void shipIsMovedBackwards() {

        ship.setY(400);
        doReturn(-100f).when(accelerator).getVerticalAcceleration();

        engine.fly();

        assertEquals(399, ship.getY(), 0);
    }

    @Test
    public void shipIsAcceleratedBackwards() {

        ship.setY(400);
        doReturn(-100f).when(accelerator).getVerticalAcceleration();

        engine.fly();
        engine.fly();

        assertEquals(-2, engine.currentSpeedVertical, 0);
    }

    @Test
    public void slowDownNearUpperEdgeOfScreen() {

        engine.currentSpeedVertical = 10;
        ship.setY(Sizes.GAME_HEIGHT - 80);
        doReturn(100f).when(accelerator).getVerticalAcceleration();

        engine.fly();

        assertEquals(9, engine.currentSpeedVertical, 0);
    }

    @Test
    public void slowDownNearLowerEdgeOfScreen() {

        engine.currentSpeedVertical = -10;
        ship.setY(Sizes.DOWN_MARGIN + 20);
        doReturn(100f).when(accelerator).getVerticalAcceleration();

        engine.fly();

        assertEquals(-9, engine.currentSpeedVertical, 0);
    }

    @Test
    public void slowDownWhenFlyingForwardButNoAcceleratorInput() {

        engine.currentSpeedVertical = 10;
        ship.setY(480);
        doReturn(0f).when(accelerator).getVerticalAcceleration();

        engine.fly();

        assertEquals(9, engine.currentSpeedVertical, 0);
    }

    @Test
    public void slowDownWhenFlyingBackwardButNoAcceleratorInput() {

        engine.currentSpeedVertical = -10;
        ship.setY(480);
        doReturn(0f).when(accelerator).getVerticalAcceleration();

        engine.fly();

        assertEquals(-9, engine.currentSpeedVertical, 0);
    }

    @Test
    public void shipIsMovedRight() {

        ship.setX(400);
        doReturn(100f).when(accelerator).getHorizontalAcceleration();

        engine.fly();

        assertEquals(401, ship.getX(), 0);

        engine.fly();
        engine.fly();

        assertEquals(406, ship.getX(), 0);
    }

    @Test
    public void shipIsAcceleratedRightBy100Percent() {

        ship.setX(400);
        doReturn(100f).when(accelerator).getHorizontalAcceleration();

        for (int i = 0; i < 15; i++) {
            engine.fly();
        }

        assertEquals(10, engine.currentSpeedHorizontal, 0);
    }

    @Test
    public void shipIsAcceleratedRightBy50Percent() {

        ship.setX(400);
        doReturn(50f).when(accelerator).getHorizontalAcceleration();

        for (int i = 0; i < 15; i++) {
            engine.fly();
        }

        assertEquals(5, engine.currentSpeedHorizontal, 0);
    }

    @Test
    public void shipIsAcceleratedLeft() {

        ship.setX(400);
        doReturn(-100f).when(accelerator).getHorizontalAcceleration();

        engine.fly();
        engine.fly();

        assertEquals(-2, engine.currentSpeedHorizontal, 0);
    }

    @Test
    public void slowDownNearRightEdgeOfScreen() {

        engine.currentSpeedHorizontal = 10;
        ship.setX(Sizes.GAME_WIDTH - 80);
        doReturn(100f).when(accelerator).getHorizontalAcceleration();

        engine.fly();

        assertEquals(9, engine.currentSpeedHorizontal, 0);
    }

    @Test
    public void slowDownWhenFlyingRightButNoAcceleratorInput() {

        engine.currentSpeedHorizontal = 10;
        ship.setX(40);
        doReturn(0f).when(accelerator).getHorizontalAcceleration();

        engine.fly();

        assertEquals(9, engine.currentSpeedHorizontal, 0);
    }

    @Test
    public void whenFlyingStraightTurnIsFront() {

        ship.setX(200);
        doReturn(0f).when(accelerator).getHorizontalAcceleration();

        assertEquals(IShip.Turn.FRONT, engine.fly());
    }

    @Test
    public void whenFlyingRightTurnIsRight() {

        engine.currentSpeedHorizontal = 5;
        ship.setX(200);
        doReturn(50f).when(accelerator).getHorizontalAcceleration();

        assertEquals(IShip.Turn.RIGHT, engine.fly());
    }

    @Test
    public void whenFlyingLeftTurnIsLeft() {

        engine.currentSpeedHorizontal = -5;
        ship.setX(200);
        doReturn(-50f).when(accelerator).getHorizontalAcceleration();

        assertEquals(IShip.Turn.LEFT, engine.fly());
    }

    @Test
    public void turniUnderThresholdIsBypassed() {

        engine.currentSpeedHorizontal = 1;
        ship.setX(200);
        doReturn(15f).when(accelerator).getHorizontalAcceleration();

        assertEquals(IShip.Turn.FRONT, engine.fly());
    }

    @Test
    public void sprinterShipWillSpeedUpWhenEnoughEnergy() {

        engine = new InputShipEngine(ship, 10, energyFunction, 8, null);
        engine.setAccelerator(accelerator);

        ship.setX(400);
        ship.setY(400);
        doReturn(100f).when(accelerator).getVerticalAcceleration();
        doReturn(true).when(energyFunction).test(anyFloat());

        engine.fly();
        engine.fly();
        engine.fly();

        assertEquals(423.4, ship.getY(), 0.01);
    }

    @Test
    public void sprinterShipWontSpeedUpWhenNotEnoughEnergy() {

        engine = new InputShipEngine(ship, 10, energyFunction, 8, null);
        engine.setAccelerator(accelerator);

        ship.setY(400);
        doReturn(100f).when(accelerator).getVerticalAcceleration();
        doReturn(false).when(energyFunction).test(anyFloat());

        engine.fly();
        engine.fly();
        engine.fly();

        assertEquals(406, ship.getY(), 0.01);
    }

    @Test
    public void engineUsesAdditionalMultiplier() {

        doReturn(3f).when(additionalMultiplier).get();

        ship.setX(400);
        ship.setY(400);
        doReturn(100f).when(accelerator).getVerticalAcceleration();

        engine.fly();
        engine.fly();
        engine.fly();

        assertEquals(418, ship.getY(), 0.01);
    }
}
