package spaceattack.game.ai;

import spaceattack.consts.Consts;
import spaceattack.game.actors.InvisibleActor;
import spaceattack.game.ships.enemy.EnemyShipsFactory;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;

public class EnemyBase extends InvisibleActor
{
	private IGameStage stage;
	private EnemyShipsFactory shipsFactory;
	private IUtils utils;
	private Radar radar;

	private FrameController fighterTimer;

	public EnemyBase(IUtils utils)
	{
		this.utils = utils;
		fighterTimer = new FrameController(utils, Consts.AI.FIGHTERS_PER_SECOND);
	}

	public void setStage(IGameStage stage)
	{
		this.stage = stage;
	}

	public void setShipsFactory(EnemyShipsFactory factory)
	{
		shipsFactory = factory;
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

		MoverAI mover = chooseMover(); // setting new dest if flight direction is wrong
		ShooterAI shooter = chooseShooter();

		IEnemyShip fighter = factory.createFighter(stage); // wpn ctrlr, wpns, pools
		fighter.setPlayerShip(radar.getPlayerShip());
		fighter.setMover(mover);
		fighter.setShooter(shooter);

		stage.addActor(fighter);
	}
}
