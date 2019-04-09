package spaceattack.game.ships;

import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.ships.enemy.IEnemyShip;

public interface IBoss extends IEnemyShip
{
	MoverType getDefaultMoverType();
	ShooterType getDefaultShooterType();
	
	default void setDefaultMoverType(MoverType type)
	{
		// do nothing;
	}
	
	default void setDefaultShooterType(ShooterType type)
	{
		// do nothing;
	}
}
