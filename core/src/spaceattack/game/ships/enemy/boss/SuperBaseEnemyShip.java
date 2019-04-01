package spaceattack.game.ships.enemy.boss;

import spaceattack.game.actors.interfaces.RequiredOnStage;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.BaseEnemyShip;

public class SuperBaseEnemyShip extends BaseEnemyShip implements RequiredOnStage, IBoss
{
	@Override
	public MoverType getDefaultMoverType() 
	{
		return MoverType.CORNERS_CHASER;
	}

	@Override
	public ShooterType getDefaultShooterType() 
	{
		return ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER;
	}
}
