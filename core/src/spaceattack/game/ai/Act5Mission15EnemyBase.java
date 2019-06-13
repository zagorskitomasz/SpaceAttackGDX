package spaceattack.game.ai;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.factories.Factories;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.powerup.IPowerUpBuilder;
import spaceattack.game.ships.enemy.boss.IFinalBossShipBuilder;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.Acts;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;

public class Act5Mission15EnemyBase extends Act5EnemyBase {

    private static final IVector ENEMY_HOME = Factories.getVectorFactory().create(Sizes.GAME_WIDTH * 0.5f,
            Sizes.GAME_HEIGHT + 3000);

    private FinalFightStage fightStage;
    private final IFinalBossShipBuilder bossShipBuilder;
    private final FrameController powerUpController;
    private final FrameController finalExplosionsController;
    private boolean areMinorsSpawning;
    private boolean fightCompleted;

    public Act5Mission15EnemyBase(final IUtils utils, final IFinalBossShipBuilder builder) {

        super(utils);
        setAct(Acts.V);
        fightStage = FinalFightStage.SPACE_STATION_I;
        bossShipBuilder = builder;
        boss = null;
        powerUpController = new FrameController(utils, Consts.AI.POWER_UP_FINAL_BOSS_FREQ);
        finalExplosionsController = new FrameController(utils, Consts.AI.FINAL_EXPLOSIONS_FREQ);
    }

    @Override
    public void setStage(final GameplayStage stage) {

        super.setStage(stage);
        fighterTimer.reset(calculateEnemySpawnFrequency(Consts.AI.FIGHTERS_PER_SECOND * 2));
    }

    @Override
    public void act(final float delta) {

        if (fightStage == null) {
            return;
        }

        tryLaunchPowerUp();
        trySpawnMinors();

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
            actHelperII();
            break;
        case SPACE_STATION_III:
            actSpaceStationIII();
            break;
        }
    }

    private void tryLaunchPowerUp() {

        if (areMinorsSpawning || !powerUpController.check()) {
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

    private void trySpawnMinors() {

        if (!fightCompleted && areMinorsSpawning && fighterTimer.check()) {
            addFighter();
        }
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

    private void actHelperII() {

        areMinorsSpawning = true;

        if (boss == null) {
            addHelperIIShip();
        }
        else {
            if (boss.isToKill()) {
                goToNextStage();
            }
        }
    }

    private void addHelperIIShip() {

        boss = bossShipBuilder.createHelperII(stage);
        addBoss();
    }

    private void goToNextStage() {

        boss = null;

        if (fightStage.nextStage != null) {
            fightStage = fightStage.nextStage;
        }
    }

    private void actSpaceStationIII() {

        if (boss == null) {
            if (!fightCompleted) {
                addSpaceStationIIIShip();
            }
            else {
                finalExplosions();
            }
        }
        else {
            if (boss.isToKill()) {
                boss = null;
                fightCompleted = true;
            }
        }
    }

    private void addSpaceStationIIIShip() {

        boss = bossShipBuilder.createSpaceStationIII(stage);
        addBoss();
    }

    private void finalExplosions() {

        if (!finalExplosionsController.check()) {
            return;
        }

        Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();
        explosion.getActor().setPosition((float) Math.random() * Sizes.GAME_WIDTH,
                (float) Math.random() * Sizes.GAME_HEIGHT);
        stage.getMissilesLauncher().launch(explosion);
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

    void setPowerUpBuilder(final IPowerUpBuilder builder) {

        powerUpBuilder = builder;
    }
}
