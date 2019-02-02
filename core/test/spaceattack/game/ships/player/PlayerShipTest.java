package spaceattack.game.ships.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.batch.IBatch;
import spaceattack.game.engines.IEngine;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.weapons.IWeapon;

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

	@Mock
	private ITexture texture;

	@Before
	public void setUp()
	{
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
		MockitoAnnotations.initMocks(this);

		doReturn(100).when(texture).getWidth();
		doReturn(200).when(texture).getHeight();

		ship = new PlayerShip();
		ship.setActor(new FakeActor());
		ship.setShipEngine(engine);
		ship.setHpPool(hpPool);
		ship.setEnergyPool(energyPool);
		ship.addWeapon(weapon);
		ship.setTexture(texture);
	}

	@Test
	public void isSettingCenterCoords()
	{
		IBatch batch = mock(IBatch.class);

		ship.draw(batch, 0);

		verify(batch).draw(texture, 310f, 300f);
	}

	@Test
	public void radius()
	{
		doReturn(100).when(texture).getWidth();
		doReturn(100).when(texture).getHeight();

		assertEquals(50f, ship.getRadius(), 0);
	}

	@Test
	public void afterDmgHigherThanHpPoolIsToKill()
	{
		IPool hpPool = new HpPool(50, 10, 5, 1);

		ship.setHpPool(hpPool);
		ship.takeDmg(100);

		assertTrue(ship.isToKill());
	}

	@Test
	public void actingIsUpdatingShipsConponents()
	{
		ship.act(0);

		verify(engine).fly();
		verify(energyPool).update();
		verify(hpPool).update();
	}

	@Test
	public void updatingLevelIsPropagatedToShipsComponents()
	{
		ship.setLevel(5);

		verify(engine).setLevel(5);
		verify(energyPool).setLevel(5);
		verify(hpPool).setLevel(5);
		verify(weapon).setLevel(5);
	}
}
