package spaceattack.game.ships.enemy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.engines.IEngine;
import spaceattack.game.weapons.IWeaponController;

public class FighterTest
{
	private Fighter fighter;

	@Mock
	private IWeaponController controller;

	@Mock
	private MoverAI mover;

	@Mock
	private ShooterAI shooter;

	@Mock
	private IEngine engine;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		fighter = new Fighter();
		fighter.setMover(mover);
		fighter.setShooter(shooter);
		fighter.setWeaponController(controller);
		fighter.setShipEngine(engine);
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

		verify(controller).performAttack(PossibleAttacks.BOTH);
	}

	@Test
	public void dontBotherControllerWhenCantAttack()
	{
		doReturn(PossibleAttacks.NONE).when(shooter).checkShot();

		fighter.act(0);

		verify(controller, times(0)).performAttack(any(PossibleAttacks.class));
	}
}