package spaceattack.game.ships.enemy;

import spaceattack.game.stages.IGameStage;

public interface IEnemyShipsFactory
{
	public IEnemyShip createFighter(IGameStage stage);
}
