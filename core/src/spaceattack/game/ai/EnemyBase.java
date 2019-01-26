package spaceattack.game.ai;

import java.util.List;

import spaceattack.consts.Consts;
import spaceattack.game.actors.InvisibleActor;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.movers.DirectChaser;
import spaceattack.game.ai.shooters.DirectShooter;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.enemy.IEnemyShipsFactory;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;

public class EnemyBase extends InvisibleActor
{
	private GameplayStage stage;
	private IEnemyShipsFactory shipsFactory;
	private Radar radar;
	private List<IEnemyShip> enemyShips;
	private RadarVisible playerShip;

	private FrameController fighterTimer;

	public EnemyBase(IUtils utils)
	{
		fighterTimer = new FrameController(utils, Consts.AI.FIGHTERS_PER_SECOND, 3000);
	}

	public void setStage(GameplayStage stage)
	{
		this.stage = stage;
	}

	public void setShipsFactory(IEnemyShipsFactory factory)
	{
		shipsFactory = factory;
	}

	public void setRadar(Radar radar)
	{
		this.radar = radar;
	}

	@Override
	public void act(float delta)
	{
		if (fighterTimer.check())
			addFighter();
	}

	private void addFighter()
	{
		updateRadar();

		IEnemyShip fighter = shipsFactory.createFighter(stage);

		MoverAI mover = chooseMover(fighter);
		ShooterAI shooter = chooseShooter(fighter);

		fighter.setPlayerShip(radar.getPlayerShip());
		fighter.setMover(mover);
		fighter.setShooter(shooter);

		stage.addActorBeforeGUI(fighter);
	}

	private MoverAI chooseMover(IEnemyShip fighter)
	{
		MoverAI mover = null;

		if (enemyShips.isEmpty())
			mover = new DirectChaser();

		// for tests
		mover = new DirectChaser();

		mover.setPlayerShip(playerShip);
		mover.setOwner(fighter);

		return mover;
	}

	private ShooterAI chooseShooter(IEnemyShip fighter)
	{
		ShooterAI shooter = null;

		if (enemyShips.isEmpty())
			shooter = new DirectShooter();

		// for tests
		shooter = new DirectShooter();

		shooter.setPlayerShip(playerShip);
		shooter.setOwner(fighter);

		return shooter;
	}

	private void updateRadar()
	{
		if (radar == null)
			return;

		radar.update();
		playerShip = radar.getPlayerShip();
		enemyShips = radar.getEnemyShips();
	}
}
