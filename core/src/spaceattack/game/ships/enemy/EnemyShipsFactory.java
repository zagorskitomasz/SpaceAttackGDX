package spaceattack.game.ships.enemy;

import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.Acts;

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

	@Override
	public IEnemyShip createMinorBoss(Acts act, GameplayStage stage) 
	{
		return MinorBossShipBuilder.INSTANCE.build(act,stage);
	}
}
