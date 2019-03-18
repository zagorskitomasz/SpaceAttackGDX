package spaceattack.game.ships.enemy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.actor.ExtActorFactory;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.batch.IBatch;
import spaceattack.game.engines.IEngine;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Burner;

public class BaseEnemyShipTest
{
	private BaseEnemyShip fighter;

	@Mock
	private IWeaponController controller;

	@Mock
	private MoverAI mover;

	@Mock
	private ShooterAI shooter;

	@Mock
	private IEngine engine;

	@Mock
	private IBatch batch;

	@Mock
	private EnemyBar bar;

	@Mock
	private Burner burner;

	@Mock
	private MissilesLauncher launcher;

	@Mock
	private IPowerUp powerUp;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		fighter = new BaseEnemyShip();
		fighter.setMover(mover);
		fighter.setShooter(shooter);
		fighter.setWeaponController(controller);
		fighter.setShipEngine(engine);
		fighter.setBar(bar);
		fighter.setBurner(burner);
		fighter.setMissilesLauncher(launcher);
		fighter.setPowerUp(powerUp);
		fighter.setActor(ExtActorFactory.INSTANCE.create(fighter));
	}

	@Test
	public void shipIsMovingWhenEngineDidntReachDestination()
	{
		doReturn(false).when(engine).isDestinationReached();
		assertTrue(fighter.isMoving());
	}

	@Test
	public void shipIsNotMovingWhenEngineReachedDestination()
	{
		doReturn(true).when(engine).isDestinationReached();
		assertFalse(fighter.isMoving());
	}

	@Test
	public void shipIsDelegatingAttackOrderToWeaponController()
	{
		doReturn(PossibleAttacks.BOTH).when(shooter).checkShot();

		fighter.act(0);

		verify(controller).performAttack(eq(PossibleAttacks.BOTH),nullable(RadarVisible.class));
	}

	@Test
	public void dontBotherControllerWhenCantAttack()
	{
		doReturn(PossibleAttacks.NONE).when(shooter).checkShot();

		fighter.act(0);

		verify(controller, times(0)).performAttack(any(PossibleAttacks.class),nullable(RadarVisible.class));
	}

	@Test
	public void barIsDrawn()
	{
		fighter.draw(batch, 0);
		verify(bar).draw(batch);
	}

	@Test
	public void powerUpIsLaunchedWhenShipDestroyed()
	{
		fighter.setToKill();

		verify(launcher).launch(powerUp);
	}
}
