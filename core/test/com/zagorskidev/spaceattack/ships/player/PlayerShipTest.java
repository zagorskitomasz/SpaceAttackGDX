package com.zagorskidev.spaceattack.ships.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.ships.HpPool;
import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public class PlayerShipTest
{
	private PlayerShip ship;

	@Mock
	private IPool energyPool;

	@Mock
	private IPool hpPool;

	@Mock
	private IEngine engine;

	@Mock
	private IWeapon weapon;

	@Before
	public void setUp()
	{
		ship = new PlayerShip();
	}

	@Test
	public void isSettingCenterCoords()
	{
		Texture texture = mock(Texture.class);
		doReturn(100).when(texture).getWidth();
		doReturn(200).when(texture).getHeight();

		Batch batch = mock(Batch.class);

		ship = spy(ship);
		ship.loadGraphics(texture);
		ship.draw(batch, 0);

		verify(batch).draw(texture, 150f, 125.000015f);
	}

	@Test
	public void radius()
	{
		ship = spy(ship);
		doReturn(100f).when(ship).getWidth();
		doReturn(100f).when(ship).getHeight();

		assertEquals(50f, ship.getRadius(), 0);
	}

	@Test
	public void afterDmgHigherThanHpPoolIsToKill()
	{
		initMocks(this);

		IPool hpPool = new HpPool(50, 10, 5, 1);

		ship.setHpPool(hpPool);
		ship.setEnergyPool(energyPool);
		ship.takeDmg(100);

		assertTrue(ship.isToKill());
	}

	@Test
	public void actingIsUpdatingShipsConponents()
	{
		initMocks(this);

		ship.setShipEngine(engine);
		ship.setEnergyPool(energyPool);
		ship.setHpPool(hpPool);

		ship.act(0);

		verify(engine).fly();
		verify(energyPool).update();
		verify(hpPool).update();
	}

	@Test
	public void updatingLevelIsPropagatedToShipsComponents()
	{
		initMocks(this);

		ship.setShipEngine(engine);
		ship.setEnergyPool(energyPool);
		ship.setHpPool(hpPool);
		ship.addWeapon(weapon);

		ship.setLevel(5);

		verify(engine).setLevel(5);
		verify(energyPool).setLevel(5);
		verify(hpPool).setLevel(5);
		verify(weapon).setLevel(5);
	}
}
