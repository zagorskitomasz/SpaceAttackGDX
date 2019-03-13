package spaceattack.game.ships.enemy;

import spaceattack.game.stages.impl.GameplayStage;

public interface IEnemyShipsFactory
{
	IEnemyShip createFighter(GameplayStage stage);

	IEnemyShip createChaser(GameplayStage stage);

	IEnemyShip createTank(GameplayStage stage);

	IEnemyShip createSuperTank(GameplayStage stage);
}
