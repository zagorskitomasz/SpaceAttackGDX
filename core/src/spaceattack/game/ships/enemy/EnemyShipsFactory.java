package spaceattack.game.ships.enemy;

import spaceattack.game.stages.impl.GameplayStage;

public enum EnemyShipsFactory implements IEnemyShipsFactory
{
	INSTANCE;

	@Override
	public IEnemyShip createFighter(GameplayStage stage)
	{
		return FighterShipBuilder.INSTANCE.build(stage);
	}

	@Override
	public IEnemyShip createChaser(GameplayStage stage) 
	{
		return ChaserShipBuilder.INSTANCE.build(stage);
	}

	@Override
	public IEnemyShip createTank(GameplayStage stage) 
	{
		return TankShipBuilder.INSTANCE.build(stage);
	}

	@Override
	public IEnemyShip createSuperTank(GameplayStage stage) 
	{
		return TankShipBuilder.INSTANCE.buildRequired(stage);
	}
}
