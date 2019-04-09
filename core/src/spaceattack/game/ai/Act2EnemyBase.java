package spaceattack.game.ai;

import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.powerup.PowerUpBuilder;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.Acts;
import spaceattack.game.utils.IUtils;

public class Act2EnemyBase extends EnemyBase 
{
	public Act2EnemyBase(IUtils utils) 
	{
		super(utils);
		setAct(Acts.II);
	}

	@Override
	protected ShooterAI createTankShooter(IEnemyShip tank) 
	{
		return createInstantPrimaryShooter(tank);
	}

	@Override
	protected IPowerUp choosePowerUp() 
	{
		return PowerUpBuilder.INSTANCE.mineHolder(controller, fireButton, stage);
	}
}
