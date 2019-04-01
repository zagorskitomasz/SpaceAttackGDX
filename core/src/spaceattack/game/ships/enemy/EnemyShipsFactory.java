package spaceattack.game.ships.enemy;

import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.boss.MajorAct1BossShipBuilder;
import spaceattack.game.ships.enemy.boss.MinorBossShipBuilder;
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
	public IBoss createMinorBoss(Acts act, GameplayStage stage) 
	{
		return MinorBossShipBuilder.INSTANCE.build(act,stage);
	}

	public IBoss createMajorAct1Boss(GameplayStage stage) 
	{
		return MajorAct1BossShipBuilder.INSTANCE.build(stage);
	}
}
