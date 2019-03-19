package spaceattack.game.ships.enemy;

import spaceattack.game.ships.IBoss;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.Acts;

public interface IEnemyShipsFactory
{
	IEnemyShip createFighter(GameplayStage stage);

	IEnemyShip createChaser(GameplayStage stage);

	IEnemyShip createTank(GameplayStage stage);

	IEnemyShip createSuperTank(GameplayStage stage);

	IBoss createMinorBoss(Acts act, GameplayStage stage);
}
