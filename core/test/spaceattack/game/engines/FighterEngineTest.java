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

        engine = new FighterEngine(ship, ExtUtilsFactory.INSTANCE.create(), 10);
    }

    @Test
    public void destinationIsOnlyHorizontal() {

        engine.setDestination(vectors.create(50, 50));

        engine.fly();

        assertEquals(vectors.create(50, 20), engine.getDestination());
    }

    @Test
    public void shipIsMovingToDestinationAndDownInBaseSpeed() {

        engine.setDestination(vectors.create(50, 50));

        engine.fly();

        assertEquals(vectors.create(22, 18), vectors.create(ship.getX(), ship.getY()));
    }
}
