package spaceattack.game.ships.enemy;

import spaceattack.game.stages.impl.GameplayStage;

public interface IEnemyShipsFactory
{
	public IEnemyShip createFighter(GameplayStage stage);

	public IEnemyShip createChaser(GameplayStage stage);
}
