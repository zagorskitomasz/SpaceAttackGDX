package spaceattack.game.ai;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.factories.Factories;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.ships.enemy.boss.IFinalBossShipBuilder;
import spaceattack.game.system.Acts;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;

public class Act5Mission15EnemyBase extends Act5EnemyBase {

    private static final IVector ENEMY_HOME = Factories.getVectorFactory().create(Sizes.GAME_WIDTH * 0.5f,
            Sizes.GAME_HEIGHT + 3000);

    private FinalFightStage fightStage;
    private final IFinalBossShipBuilder bossShipBuilder;
    private final FrameController powerUpController;

    public Act5Mission15EnemyBase(final IUtils utils, final IFinalBossShipBuilder builder) {

        super(utils);
        setAct(Acts.V);
        fightStage = FinalFightStage.SPACE_STATION_I;
        bossShipBuilder = builder;
        boss = null;
        powerUpController = new FrameController(utils, Consts.AI.POWER_UP_FINAL_BOSS_FREQ);
    }

    @Override
    public void act(final float delta) {

        if (fightStage == null) {
            return;
        }

        tryLaunchPowerUp();

        switch (fightStage) {
        case SPACE_STATION_I:
            actSpaceStationI();
            break;
        case HELPER_I:
            actHelperI();
            break;
        case SPACE_STATION_II:
            actSpaceStationII();
            break;
        case HELPER_II:
            break;
        case SPACE_STATION_III:
            break;
        }
    }

    private void tryLaunchPowerUp() {

        if (!powerUpController.check()) {
            return;
        }

        IPowerUp powerUp = choosePowerUp();

        if (powerUp == null) {
            return;
        }

        powerUp.setX((float) (Sizes.GAME_WIDTH * 0.1f + Math.random() * Sizes.GAME_WIDTH * 0.8f));
        powerUp.setY(Sizes.GAME_HEIGHT * 1.1f);
        stage.getMissilesLauncher().launch(powerUp);
    }

    private void actSpaceStationI() {

        if (boss == null) {
            addSpaceStationIShip();
        }
        else {
            if (isHpPercentBelow(0.67f)) {
                sendBossHome();
                goToNextStage();
            }
        }
    }

    private void addSpaceStationIShip() {

        boss = bossShipBuilder.createSpaceStationI(stage);
        addBoss();
    }

    private boolean isHpPercentBelow(final float percent) {

        return boss.getHpPool().getAmount() < boss.getHpPool().getMaxAmount() * percent;
    }

    private void sendBossHome() {

        boss.getHpPool().addTemporalInfinityChecker(() -> true);
        boss.setDestination(ENEMY_HOME);
    }

    private void actHelperI() {

        if (boss == null) {
            addHelperIShip();
        }
        else {
            if (boss.isToKill()) {
                goToNextStage();
            }
        }
    }

    private void addHelperIShip() {

        boss = bossShipBuilder.createHelperI(stage);
        addBoss();
    }

    private void actSpaceStationII() {

        if (boss == null) {
            addSpaceStationIIShip();
        }
        else {
            if (isHpPercentBelow(0.33f)) {
                sendBossHome();
                goToNextStage();
            }
        }
    }

    private void addSpaceStationIIShip() {

        boss = bossShipBuilder.createSpaceStationII(stage);
        addBoss();
    }

    private void goToNextStage() {

        boss = null;

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
