package spaceattack.game.engines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.IShip.Turn;

public class ShipEngineTest
{
	private IShip ship;

	private DestinationShipEngine engine;

	@Before
	public void setUp()
	{
		Factories.setVectorFactory(ExtVectorFactory.INSTANCE);

		ship = new FakeShip();

		ship.setX(10);
		ship.setY(20);

		engine = new DestinationShipEngine(ship, ExtUtilsFactory.INSTANCE.create());
		engine.setBaseSpeed(2);
		engine.setAcceleration(1);
		engine.setBraking(1);
		engine.setAgility(2);
		engine.setLevel(1);
	}

	@Test
	public void settingDestinationOnFreshEngine()
	{
		engine.setDestination(ExtVectorFactory.INSTANCE.create(30, 30));

		assertEquals(30, engine.getDestination().getX(), 0);
		assertEquals(30, engine.getDestination().getY(), 0);

		assertNull(engine.getNextDestination());
	}

	@Test
	public void settingNextDestination()
	{
		engine.setDestination(ExtVectorFactory.INSTANCE.create(30, 30));
		engine.setDestination(ExtVectorFactory.INSTANCE.create(40, 40));

		assertEquals(30, engine.getDestination().getX(), 0);
		assertEquals(30, engine.getDestination().getY(), 0);

		assertEquals(40, engine.getNextDestination().getX(), 0);
		assertEquals(40, engine.getNextDestination().getY(), 0);
	}

	@Test
	public void flyToNoDestination()
	{
		assertEquals(Turn.FRONT, engine.fly());
	}

	@Test
	public void flyToReachedDestination()
	{
		engine.setDestination(ExtVectorFactory.INSTANCE.create(10, 20));
		assertEquals(Turn.FRONT, engine.fly());
	}

	@Test
	public void turn()
	{
		engine.setDestination(ExtVectorFactory.INSTANCE.create(30, 30));
		engine.setDestination(ExtVectorFactory.INSTANCE.create(40, 40));
		ship.setX(30);
		ship.setY(30);

		engine.fly();

		assertEquals(ExtVectorFactory.INSTANCE.create(40, 40), engine.getDestination());
	}

	@Test
	public void isAcceleratingAndBraking()
	{
		engine.setDestination(ExtVectorFactory.INSTANCE.create(30, 20));

		engine.fly();
		engine.fly();
		engine.fly();
		assertEquals(5, engine.getCurrentSpeed(), 0);

		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();

		assertEquals(2, engine.getCurrentSpeed(), 0);
	}

	@Test
	public void isTurning()
	{
		engine.setDestination(ExtVectorFactory.INSTANCE.create(100, 20));

		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();

		engine.setDestination(ExtVectorFactory.INSTANCE.create(100, 30));

		engine.fly();

		assertEquals(ExtVectorFactory.INSTANCE.create(100, 30), engine.getDestination());
	}

	@Test
	public void isSlowingBeforeTurnTurning()
	{
		engine.setDestination(ExtVectorFactory.INSTANCE.create(100, 20));

		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		assertEquals(8, engine.getCurrentSpeed(), 0);

		engine.setDestination(ExtVectorFactory.INSTANCE.create(1, 21));

		engine.fly();

		assertEquals(7, engine.getCurrentSpeed(), 0);
		assertEquals(ExtVectorFactory.INSTANCE.create(100, 20), engine.getDestination());
		assertEquals(ExtVectorFactory.INSTANCE.create(1, 21), engine.getNextDestination());
	}

	@Test
	public void increasingLevel()
	{
		engine.setLevel(3);

		assertEquals(2.8f, engine.getCurrentSpeed(), 0.01);
	}
}
