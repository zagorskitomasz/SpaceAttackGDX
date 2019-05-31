package spaceattack.game.ai;

import spaceattack.consts.Sizes;
import spaceattack.game.ai.movers.Follower;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.enemy.boss.IFinalBossShipBuilder;
import spaceattack.game.system.Acts;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;

public class Act5Mission15EnemyBase extends Act5EnemyBase {

    private static final IVector ENEMY_HOME = Factories.getVectorFactory().create(Sizes.GAME_WIDTH * 0.5f,
            Sizes.GAME_HEIGHT + 3000);

    private FinalFightStage fightStage;
    private final IFinalBossShipBuilder bossShipBuilder;

    public Act5Mission15EnemyBase(final IUtils utils, final IFinalBossShipBuilder builder) {

        super(utils);
        setAct(Acts.V);
        fightStage = FinalFightStage.SPACE_STATION_I;
        bossShipBuilder = builder;
        boss = null;
    }

    @Override
    public void act(final float delta) {

        if (fightStage == null) {
            return;
        }

        switch (fightStage) {
        case SPACE_STATION_I:
            actSpaceStationI();
            break;
        case HELPER_I:
            break;
        case SPACE_STATION_II:
            break;
        case HELPER_II:
            break;
        case SPACE_STATION_III:
            break;
        }
    }

    private void actSpaceStationI() {

        if (boss == null) {
            addSpaceStationIShip();
        }
        else
            if (isHpPercentBelow(0.67f)) {
                sendBossHome();
                goToNextStage();
            }
    }

    private void addSpaceStationIShip() {

        boss = bossShipBuilder.createSpaceStationI(stage);
        addBoss();

        IEnemyShip leftWeaponHolder = bossShipBuilder.createWeaponHolder(stage);
        IEnemyShip rightWeaponHolder = bossShipBuilder.createWeaponHolder(stage);

        addInvisibleShip(leftWeaponHolder, Direction.LEFT);
        addInvisibleShip(rightWeaponHolder, Direction.RIGHT);
    }

    private void addInvisibleShip(final IEnemyShip holder, final Direction direction) {

        ShooterAI shooter = ShooterType.INSTANT_SHOOTER.create();
        shooter.setFriends(enemyShips);
        shooter.setOwner(holder);
        shooter.setPlayerShip(playerShip);

        float xFactor = Direction.LEFT.equals(direction) ? -1 : 1;

        Follower mover = (Follower) MoverType.FOLLOWER.create();
        mover.setPlayerShip(playerShip);
        mover.setOwner(holder);
        mover.setCoordsSupplier(() -> Factories.getVectorFactory()
                .create(boss.getX() + (boss.getRadius() * 0.45f * xFactor), boss.getY() - boss.getRadius() * 0.45f));

        holder.setMover(mover);
        holder.setShooter(shooter);
        holder.getHpPool().addTemporalInfinityChecker(() -> true);
        holder.setPlayerShip(playerShip);

        stage.addActorBeforeGUI(holder);
    }

    private boolean isHpPercentBelow(final float percent) {

        return boss.getHpPool().getAmount() < boss.getHpPool().getMaxAmount() * percent;
    }

    private void sendBossHome() {

        boss.getHpPool().addTemporalInfinityChecker(() -> true);
        boss.setDestination(ENEMY_HOME);
    }

    private void goToNextStage() {

        if (fightStage.nextStage != null) {
            fightStage = fightStage.nextStage;
        }
    }

    private enum FinalFightStage {

        SPACE_STATION_III(null),
        HELPER_II(SPACE_STATION_III),
        SPACE_STATION_II(HELPER_II),
        HELPER_I(SPACE_STATION_II),
        SPACE_STATION_I(HELPER_I);

        private FinalFightStage nextStage;

        FinalFightStage(final FinalFightStage nextStage) {

            this.nextStage = nextStage;
        }
    }
}
