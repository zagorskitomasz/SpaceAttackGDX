package spaceattack.game.ships.enemy;

import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.boss.MajorBossShipBuilder;
import spaceattack.game.ships.enemy.boss.MinorBossShipBuilder;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.Acts;

public enum EnemyShipsFactory implements IEnemyShipsFactory
{
	INSTANCE;

	@Override
	public IEnemyShip createFighter(Acts act, GameplayStage stage)
	{
		switch(act)
		{
		case I:
			return FighterShipBuilder.INSTANCE.buildActI(stage);
		case II:
			return FighterShipBuilder.INSTANCE.buildActII(stage);
		default:
			return null;
		}
	}

	@Override
	public IEnemyShip createChaser(Acts act, GameplayStage stage) 
	{
		switch(act)
		{
		case I:
			return ChaserShipBuilder.INSTANCE.buildActI(stage);
		case II:
			return ChaserShipBuilder.INSTANCE.buildActII(stage);
		default:
			return null;
		}
	}

	@Override
	public IEnemyShip createTank(Acts act, GameplayStage stage) 
	{
		switch(act)
		{
		case I:
			return TankShipBuilder.INSTANCE.buildActI(stage, false);
		case II:
			return TankShipBuilder.INSTANCE.buildActII(stage, false);
		default:
			return null;
		}
	}

	@Override
	public IEnemyShip createSuperTank(Acts act, GameplayStage stage) 
	{
		switch(act)
		{
		case I:
			return TankShipBuilder.INSTANCE.buildActI(stage, true);
		case II:
			return TankShipBuilder.INSTANCE.buildActII(stage, true);
		default:
			return null;
		}
	}

	@Override
	public IBoss createMinorBoss(Acts act, GameplayStage stage) 
	{
		switch(act)
		{
		case I:
			return MinorBossShipBuilder.INSTANCE.buildActI(stage);
		case II:
			return MinorBossShipBuilder.INSTANCE.buildActII(stage);
		default:
			return null;
		}
	}

	public IBoss createMajorBoss(Acts act, GameplayStage stage) 
	{
		switch(act)
		{
		case I:
			return MajorBossShipBuilder.INSTANCE.buildActI(stage);
		case II:
			return null;
		default:
			return null;
		}
	}
}
