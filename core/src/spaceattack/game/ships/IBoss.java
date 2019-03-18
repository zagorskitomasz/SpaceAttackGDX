package spaceattack.game.ships;

import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.ships.enemy.IEnemyShip;

public interface IBoss extends IEnemyShip
{
	MoverType getDefaultMoverType();
	ShooterType getDefaultShooterType();
}
