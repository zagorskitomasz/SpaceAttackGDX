package spaceattack.game.ships.enemy;

import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.boss.MajorBossShipBuilder;
import spaceattack.game.ships.enemy.boss.MinorBossShipBuilder;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.Acts;

public enum EnemyShipsFactory implements IEnemyShipsFactory {
    INSTANCE;

    @Override
    public IEnemyShip createFighter(final Acts act, final GameplayStage stage) {

        switch (act) {
        case I:
            return FighterShipBuilder.INSTANCE.buildActI(stage);
        case II:
            return FighterShipBuilder.INSTANCE.buildActII(stage);
        case III:
            return FighterShipBuilder.INSTANCE.buildActIII(stage);
        case IV:
            return FighterShipBuilder.INSTANCE.buildActIV(stage);
        default:
            return null;
        }
    }

    @Override
    public IEnemyShip createChaser(final Acts act, final GameplayStage stage) {

        switch (act) {
        case I:
            return ChaserShipBuilder.INSTANCE.buildActI(stage);
        case II:
            return ChaserShipBuilder.INSTANCE.buildActII(stage);
        case III:
            return ChaserShipBuilder.INSTANCE.buildActIII(stage);
        case IV:
            return ChaserShipBuilder.INSTANCE.buildActIV(stage);
        default:
            return null;
        }
    }

    @Override
    public IEnemyShip createTank(final Acts act, final GameplayStage stage) {

        switch (act) {
        case I:
            return TankShipBuilder.INSTANCE.buildActI(stage, false);
        case II:
            return TankShipBuilder.INSTANCE.buildActII(stage, false);
        case III:
            return TankShipBuilder.INSTANCE.buildActIII(stage, false);
        case IV:
            return TankShipBuilder.INSTANCE.buildActIV(stage, false);
        default:
            return null;
        }
    }

    @Override
    public IEnemyShip createSuperTank(final Acts act, final GameplayStage stage) {

        switch (act) {
        case I:
            return TankShipBuilder.INSTANCE.buildActI(stage, true);
        case II:
            return TankShipBuilder.INSTANCE.buildActII(stage, true);
        case III:
            return TankShipBuilder.INSTANCE.buildActIII(stage, true);
        case IV:
            return TankShipBuilder.INSTANCE.buildActIV(stage, true);
        default:
            return null;
        }
    }

    @Override
    public IBoss createMinorBoss(final Acts act, final GameplayStage stage) {

        switch (act) {
        case I:
            return MinorBossShipBuilder.INSTANCE.buildActI(stage);
        case II:
            return MinorBossShipBuilder.INSTANCE.buildActII(stage);
        case III:
            return MinorBossShipBuilder.INSTANCE.buildActIII(stage);
        case IV:
            return MinorBossShipBuilder.INSTANCE.buildActIV(stage);
        default:
            return null;
        }
    }

    public IBoss createMajorBoss(final Acts act, final GameplayStage stage) {

        switch (act) {
        case I:
            return MajorBossShipBuilder.INSTANCE.buildActI(stage);
        case II:
            return MajorBossShipBuilder.INSTANCE.buildActII(stage);
        case III:
            return MajorBossShipBuilder.INSTANCE.buildActIII(stage);
        default:
            return null;
        }
    }
}
