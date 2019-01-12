package spaceattack.game.engines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.zagorskidev.spaceattack.ships.IShip.Turn;
import com.zagorskidev.spaceattack.ships.player.PlayerShip;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.factories.Factories;

public class ShipEngineTest
{
	@Mock
	private PlayerShip ship;

	private ShipEngine engine;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		Factories.setVectorFactory(ExtVectorFactory.INSTANCE);

		doCallRealMethod().when(ship).getX();
		doCallRealMethod().when(ship).getY();
		doCallRealMethod().when(ship).setX(anyFloat());
		doCallRealMethod().when(ship).setY(anyFloat());
		doCallRealMethod().when(ship).setLevel(anyInt());
		doCallRealMethod().when(ship).setShipEngine(any(IEngine.class));

		ship.setX(10);
		ship.setY(20);

		engine = new ShipEngine(ship, ExtUtilsFactory.INSTANCE.create());
		engine.setBaseSpeed(2);
		engine.setAcceleration(1);
		engine.setBraking(1);
		engine.setAgility(2);

		ship.setShipEngine(engine);
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
		doReturn(30f).when(ship).getX();
		doReturn(30f).when(ship).getY();

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
		ship.setLevel(3);

		assertEquals(2.8f, engine.getCurrentSpeed(), 0.01);
	}
}
