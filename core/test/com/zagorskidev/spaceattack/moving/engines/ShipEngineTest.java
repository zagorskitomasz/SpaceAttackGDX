package com.zagorskidev.spaceattack.moving.engines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import com.badlogic.gdx.math.Vector2;
import com.zagorskidev.spaceattack.ships.IShip.Turn;
import com.zagorskidev.spaceattack.ships.player.PlayerShip;

public class ShipEngineTest
{
	private PlayerShip ship;
	private ShipEngine engine;

	@Before
	public void setUp()
	{
		ship = mock(PlayerShip.class);

		doCallRealMethod().when(ship).getX();
		doCallRealMethod().when(ship).getY();
		doCallRealMethod().when(ship).setX(ArgumentMatchers.anyFloat());
		doCallRealMethod().when(ship).setY(ArgumentMatchers.anyFloat());

		ship.setX(10);
		ship.setY(20);

		//@formatter:off
		engine = ShipEngineBuilder
				.INSTANCE
				.getBuilder(ship)
				.setBaseSpeed(2)
				.setAcceleration(1)
				.setBraking(1)
				.setAgility(2)
				.build();
		//@formatter.on
	}

	@Test
	public void settingDestinationOnFreshEngine()
	{
		engine.setDestination(new Vector2(30,30));
		
		assertEquals(30, engine.getDestination().x,0);
		assertEquals(30, engine.getDestination().y,0);
		
		assertNull(engine.getNextDestination());
	}

	@Test
	public void settingNextDestination()
	{
		engine.setDestination(new Vector2(30,30));
		engine.setDestination(new Vector2(40,40));
		
		assertEquals(30, engine.getDestination().x,0);
		assertEquals(30, engine.getDestination().y,0);
		
		assertEquals(40, engine.getNextDestination().x,0);
		assertEquals(40, engine.getNextDestination().y,0);
	}
	
	@Test
	public void flyToNoDestination()
	{
		assertEquals(Turn.FRONT, engine.fly());
	}
	
	@Test
	public void flyToReachedDestination()
	{
		engine.setDestination(new Vector2(10,20));
		assertEquals(Turn.FRONT, engine.fly());
	}
	
	@Test
	public void turn()
	{
		engine.setDestination(new Vector2(30,30));
		engine.setDestination(new Vector2(40,40));
		doReturn(30f).when(ship).getX();
		doReturn(30f).when(ship).getY();
		
		engine.fly();
		
		assertEquals(new Vector2(40,40), engine.getDestination());
	}
	
	@Test
	public void isAcceleratingAndBraking()
	{
		engine.setDestination(new Vector2(30,20));
		
		engine.fly();
		engine.fly();
		engine.fly();
		assertEquals(5,engine.getCurrentSpeed(),0);
		
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		
		assertEquals(2,engine.getCurrentSpeed(),0);
	}
	
	@Test
	public void isTurning()
	{
		engine.setDestination(new Vector2(100,20));
		
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		
		engine.setDestination(new Vector2(100,30));
		
		engine.fly();
		
		assertEquals(new Vector2(100,30), engine.getDestination());
	}
	
	@Test
	public void isSlowingBeforeTurnTurning()
	{
		engine.setDestination(new Vector2(100,20));
		
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		engine.fly();
		assertEquals(8,engine.getCurrentSpeed(),0);
		
		engine.setDestination(new Vector2(1,21));
		
		engine.fly();

		assertEquals(7,engine.getCurrentSpeed(),0);
		assertEquals(new Vector2(100,20), engine.getDestination());
		assertEquals(new Vector2(1,21), engine.getNextDestination());
	}
}
