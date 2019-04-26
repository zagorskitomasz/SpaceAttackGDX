package spaceattack.game.engines;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.vector.IVectorFactory;

public class FighterEngineTest {

    private IShip ship;
    private IVectorFactory vectors;

    private FighterEngine engine;

    @Before
    public void setUp() {

        vectors = ExtVectorFactory.INSTANCE;
        Factories.setVectorFactory(vectors);

        ship = new FakeShip();

        ship.setX(20);
        ship.setY(20);

        engine = new FighterEngine(ship, ExtUtilsFactory.INSTANCE.create());
        engine.setBaseSpeed(2);
        engine.setAcceleration(1);
        engine.setBraking(1);
        engine.setAgility(2);
        engine.setLevel(1);
    }

    @Test
    public void destinationIsOnlyHorizontal() {

        engine.setDestination(vectors.create(50, 50));

        engine.fly();

        assertEquals(vectors.create(50, 20), engine.getDestination());
    }

    @Test
    public void shipIsMovingToDestinationAndDownInBaseSpeed() {

        engine.setLevel(3);
        engine.setDestination(vectors.create(50, 50));

        engine.fly();

        assertEquals(vectors.create(24.2f, 17.2f), vectors.create(ship.getX(), ship.getY()));
    }
}
