package spaceattack.game.ai;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.actor.ExtActorFactory;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.ai.movers.DirectChaser;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.movers.RightSideChaser;
import spaceattack.game.ai.shooters.DirectShooter;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.enemy.IEnemyShipsFactory;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.graphics.Textures;

public class EnemyBaseTest
{
	private EnemyBase base;

	@Mock
	private GameplayStage stage;

	@Mock
	private IEnemyShip fighter;

	@Mock
	private Radar radar;

	@Mock
	private IEnemyShipsFactory factory;

	@Mock
	private IEnemyShip directMover;

	@Mock
	private IEnemyShip leftMover;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		Factories.setActorFactory(ExtActorFactory.INSTANCE);
		Textures.loadForTest();
		doReturn(fighter).when(factory).createFighter(stage);

		base = new EnemyBase(ExtUtilsFactory.INSTANCE.create());
		base.setActor(new FakeActor());
		base.setRadar(radar);
		base.setShipsFactory(factory);
		base.setStage(stage);
	}

	@Test
	public void firstFighterIsDirectChaser() throws InterruptedException
	{
		doReturn(new ArrayList<IEnemyShip>()).when(radar).getEnemyShips();

		Thread.sleep(3000);
		base.act(0);

		verify(fighter).setMover(any(DirectChaser.class));
	}

	@Test
	public void firstFighterIsDirectShooter() throws InterruptedException
	{
		doReturn(new ArrayList<IEnemyShip>()).when(radar).getEnemyShips();

		Thread.sleep(3000);
		base.act(0);

		verify(fighter).setShooter(any(DirectShooter.class));
	}

	@Test
	public void firstFighterIsAddedAfterInitialDelay()
	{
		doReturn(new ArrayList<IEnemyShip>()).when(radar).getEnemyShips();

		base.act(0);

		verify(factory, times(0)).createFighter(any());
	}

	@Test
	public void nextFighterHasMoverWithMinOccurencesOnBattlefield() throws InterruptedException
	{
		doReturn(MoverType.DIRECT_CHASER).when(directMover).getMoverType();
		doReturn(MoverType.LEFT_SIDE_CHASER).when(leftMover).getMoverType();
		doReturn(Arrays.asList(directMover, leftMover)).when(radar).getEnemyShips();

		Thread.sleep(3000);
		base.act(0);

		verify(fighter).setMover(any(RightSideChaser.class));
	}
}
