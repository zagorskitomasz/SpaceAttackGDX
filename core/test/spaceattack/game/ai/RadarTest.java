package spaceattack.game.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.actors.IGameActor;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.player.PlayerShip;

public class RadarTest
{
	private Radar radar;

	private List<IGameActor> actors;

	@Mock
	private PlayerShip playerShip;

	@Mock
	private IEnemyShip enemyShip;

	@Mock
	private IEnemyShip enemyShip2;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		actors = new LinkedList<>();
		actors.add(playerShip);
		actors.add(enemyShip);

		radar = new Radar(actors);
	}

	@Test
	public void playerShipIsVisibleOnRadar()
	{
		radar.update();
		assertEquals(playerShip, radar.getPlayerShip());
	}

	@Test
	public void enemyShipIsVisibleOnRadar()
	{
		radar.update();
		assertTrue(radar.getEnemyShips().contains(enemyShip));
	}

	@Test
	public void radarUpdateIsCheckingActors()
	{
		actors.add(enemyShip2);
		radar.update();
		assertTrue(radar.getEnemyShips().contains(enemyShip2));
	}
}
