package spaceattack.game.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.consts.Consts;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.player.PlayerShip;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;

public class PoolIncreaserTest
{
	private PoolIncreaser increaser;

	private List<IGameActor> actors;

	@Mock
	private PlayerShip playerShip;

	@Mock
	private IEnemyShip enemyShip;

	@Mock
	private IPool pool;

	@Before
	public void setUp()
	{
		Textures.loadForTest();
		Sounds.loadForTest();

		MockitoAnnotations.initMocks(this);
		Factories.setVectorFactory(ExtVectorFactory.INSTANCE);
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

		actors = new LinkedList<>();
		actors.add(playerShip);
		actors.add(enemyShip);

		IActor actor = new FakeActor();
		actor.setPosition(100, 100);

		increaser = new PoolIncreaser();
		increaser.setActors(actors);
		increaser.setActor(actor);
		increaser.setPool(pool);
		increaser.setIncreasePercent(0.4f);

		doReturn(120f).when(playerShip).getX();
		doReturn(120f).when(playerShip).getY();
		doReturn(100f).when(pool).getMaxAmount();
	}

	@Test
	public void checksOnlyPowerUpConsumers()
	{
		increaser.act(0);

		verify(playerShip).getX();
		verify(enemyShip, times(0)).getX();
	}

	@Test
	public void consumedWhenCollision()
	{
		increaser.act(0);

		verify(playerShip).consume(increaser);
	}

	@Test
	public void isMovingDown()
	{
		increaser.act(0);

		assertEquals(100 - Consts.AI.POWER_UP_SPEED, increaser.getY(), 0);
	}

	@Test
	public void destroyedWhenConsumed()
	{
		increaser.act(0);

		assertTrue(increaser.isToKill());
	}

	@Test
	public void poolIsRegenerated()
	{
		increaser.consumed();

		verify(pool).regen(40f);
	}
}
