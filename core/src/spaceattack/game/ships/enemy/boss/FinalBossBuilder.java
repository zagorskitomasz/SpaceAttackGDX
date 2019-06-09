package spaceattack.game.ships.enemy.boss;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.ai.EnemyBase.Direction;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.BigEnemyBar;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.ships.pools.Pool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.AIWeaponController;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.miner.FlyingMiner;
import spaceattack.game.weapons.miner.FlyingMinerBuilder;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.rocketMissile.RocketMissileBuilder;
import spaceattack.game.weapons.shield.EnergyShieldEmiter;
import spaceattack.game.weapons.shield.ShieldBuilder;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaser;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;
import spaceattack.game.weapons.timeWave.TimeWaveEmitterBuilder;
import spaceattack.game.weapons.tripleGreenLaser.TripleGreenLaserBuilder;

public enum FinalBossBuilder implements IFinalBossShipBuilder {
    INSTANCE;

    @Override
    public IBoss createSpaceStationI(final GameplayStage stage) {

        IBoss boss = buildBasicSpaceStation(stage, false);

        boss.setLevel(stage.getCurrentMission() * 2);

        return boss;
    }

    private IBoss buildBasicSpaceStation(final GameplayStage stage, final boolean required) {

        IBoss boss;
        if (required) {
            boss = new RequiredBossShip();
        }
        else {
            boss = new BossShip();
        }

        Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();

        Burner burner = BurnerBuilder.INSTANCE.build(boss);

        IPool energyPool = new Pool(
                Consts.Pools.MAJOR_BOSS_ENERGY_BASE_AMOUNT,
                Consts.Pools.MAJOR_BOSS_ENERGY_INCREASE_PER_LEVEL,
                Consts.Pools.MAJOR_BOSS_ENERGY_BASE_REGEN * 0.5f,
                Consts.Pools.MAJOR_BOSS_ENERGY_REGEN_PER_LEVEL);

        IPool hpPool = new HpPool(
                Consts.Pools.MAJOR_BOSS_HP_BASE_AMOUNT * 6,
                Consts.Pools.MAJOR_BOSS_HP_INCREASE_PER_LEVEL * 6,
                Consts.Pools.MAJOR_BOSS_HP_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_HP_REGEN_PER_LEVEL);

        boss.setActor(Factories.getActorFactory().create(boss));
        boss.setEnergyPool(energyPool);
        boss.setHpPool(hpPool);
        boss.setExplosion(explosion);
        boss.setBar(new BigEnemyBar(boss));
        boss.setBurner(burner);
        boss.setX(Sizes.GAME_WIDTH * 0.2f + (float) Math.random() * Sizes.GAME_WIDTH * 0.6f);

        boss.setDefaultMoverType(MoverType.CENTRAL_STATION);
        boss.setDefaultShooterType(ShooterType.INSTANT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        controller.setShip(boss);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);

        EnergyShieldEmiter emitter = (EnergyShieldEmiter) ShieldBuilder.INSTANCE.build(controller, launcher);
        emitter.setNoEnergyCost();
        emitter.setInterval(0.25f);
        emitter.setShieldDuration(3000);
        controller.addPassiveWeapon(emitter);
        boss.addWeapon(emitter);

        addPassiveLaser(boss, launcher, null);
        addPassiveLaser(boss, launcher, Direction.LEFT);
        addPassiveLaser(boss, launcher, Direction.RIGHT);

        boss.setShipEngine(engine);
        boss.setTexture(Textures.SPACE_STATION.getTexture());
        return boss;
    }

    protected void addPassiveLaser(final IBoss boss, final MissilesLauncher launcher, final Direction direction) {

        TargetedRedLaser centralLaser = (TargetedRedLaser) TargetedRedLaserBuilder.INSTANCE
                .build(boss.getWeaponController(), launcher);
        centralLaser.setNoEnergyCost();
        centralLaser.setDistanceFromShip(0);
        if (direction == null) {
            centralLaser.setGunMovement(0, -0.8f);
        }
        else {
            centralLaser.setGunMovement(direction.getFactor() * 0.8f, 0);
        }
        boss.getWeaponController().addPassiveWeapon(centralLaser);
        boss.addWeapon(centralLaser);
    }

    @Override
    public IBoss createHelperI(final GameplayStage stage) {

        IBoss boss = new BossShip();
        Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();

        Burner burner = BurnerBuilder.INSTANCE.build(boss);

        IPool energyPool = new Pool(
                Consts.Pools.MAJOR_BOSS_ENERGY_BASE_AMOUNT,
                Consts.Pools.MAJOR_BOSS_ENERGY_INCREASE_PER_LEVEL,
                Consts.Pools.MAJOR_BOSS_ENERGY_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_ENERGY_REGEN_PER_LEVEL);

        IPool hpPool = new HpPool(
                Consts.Pools.MAJOR_BOSS_HP_BASE_AMOUNT * 2,
                Consts.Pools.MAJOR_BOSS_HP_INCREASE_PER_LEVEL * 2,
                Consts.Pools.MAJOR_BOSS_HP_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_HP_REGEN_PER_LEVEL);

        boss.setActor(Factories.getActorFactory().create(boss));
        boss.setEnergyPool(energyPool);
        boss.setHpPool(hpPool);
        boss.setExplosion(explosion);
        boss.setBar(new BigEnemyBar(boss));
        boss.setBurner(burner);
        boss.setX(Sizes.GAME_WIDTH * 0.2f + (float) Math.random() * Sizes.GAME_WIDTH * 0.6f);

        boss.setDefaultMoverType(MoverType.CORRECTABLE_CLOSE_FRONT_CHASER);
        boss.setDefaultShooterType(ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        controller.setShip(boss);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);
        IEngine engine = ShipEngineBuilder.INSTANCE.createFastDestinationEngine(boss);

        IWeapon rocketMissile = RocketMissileBuilder.INSTANCE.build(controller, launcher);
        rocketMissile.setNoEnergyCost();
        rocketMissile.setInterval(0.4f);
        controller.setPrimaryWeapon(rocketMissile);
        boss.addWeapon(rocketMissile);

        IWeapon greenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher);
        controller.setSecondaryWeapon(greenLaser);
        boss.addWeapon(greenLaser);

        boss.setShipEngine(engine);
        boss.setTexture(Textures.BOSS_HELPER_1.getTexture());

        boss.setLevel(stage.getCurrentMission() * 2);

        return boss;
    }

    @Override
    public IBoss createSpaceStationII(final GameplayStage stage) {

        IBoss boss = buildBasicSpaceStation(stage, false);

        FlyingMiner miner = (FlyingMiner) FlyingMinerBuilder.INSTANCE.build(boss.getWeaponController(),
                stage.getMissilesLauncher());
        miner.setInterval(0.33f);
        miner.setDistanceToShip(-0.2f);
        boss.getWeaponController().setPrimaryWeapon(miner);
        boss.addWeapon(miner);

        boss.setLevel(stage.getCurrentMission() * 2);
        boss.getHpPool().take(boss.getHpPool().getMaxAmount() * 0.33f);

        return boss;
    }

    @Override
    public IBoss createHelperII(final GameplayStage stage) {

        IBoss boss = new BossShip();
        Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();

        Burner burner = BurnerBuilder.INSTANCE.build(boss);

        IPool energyPool = new Pool(
                Consts.Pools.MAJOR_BOSS_ENERGY_BASE_AMOUNT,
                Consts.Pools.MAJOR_BOSS_ENERGY_INCREASE_PER_LEVEL,
                Consts.Pools.MAJOR_BOSS_ENERGY_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_ENERGY_REGEN_PER_LEVEL);

        IPool hpPool = new HpPool(
                Consts.Pools.MAJOR_BOSS_HP_BASE_AMOUNT * 2,
                Consts.Pools.MAJOR_BOSS_HP_INCREASE_PER_LEVEL * 2,
                Consts.Pools.MAJOR_BOSS_HP_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_HP_REGEN_PER_LEVEL);

        boss.setActor(Factories.getActorFactory().create(boss));
        boss.setEnergyPool(energyPool);
        boss.setHpPool(hpPool);
        boss.setExplosion(explosion);
        boss.setBar(new BigEnemyBar(boss));
        boss.setBurner(burner);
        boss.setX(Sizes.GAME_WIDTH * 0.2f + (float) Math.random() * Sizes.GAME_WIDTH * 0.6f);

        boss.setDefaultMoverType(MoverType.ALL_CORNERS_CHASER);
        boss.setDefaultShooterType(ShooterType.INSTANT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        controller.setShip(boss);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);

        IWeapon waveEmitter = TimeWaveEmitterBuilder.INSTANCE.build(controller, launcher);
        waveEmitter.setNoEnergyCost();
        waveEmitter.setInterval(0.25f);
        controller.setPrimaryWeapon(waveEmitter);
        controller.setSecondaryWeapon(waveEmitter);
        boss.addWeapon(waveEmitter);

        boss.setShipEngine(engine);
        boss.setTexture(Textures.BOSS_HELPER_2.getTexture());

        boss.setLevel(stage.getCurrentMission() * 2);

        return boss;
    }

    @Override
    public IBoss createSpaceStationIII(final GameplayStage stage) {

        IBoss boss = buildBasicSpaceStation(stage, true);

        FlyingMiner miner = (FlyingMiner) FlyingMinerBuilder.INSTANCE.build(boss.getWeaponController(),
                stage.getMissilesLauncher());
        miner.setInterval(0.33f);
        miner.setDistanceToShip(-0.2f);
        boss.getWeaponController().setPrimaryWeapon(miner);
        boss.addWeapon(miner);

        IWeapon timeWave = TimeWaveEmitterBuilder.INSTANCE.build(boss.getWeaponController(),
                stage.getMissilesLauncher());
        timeWave.setInterval(0.14f);
        boss.getWeaponController().setSecondaryWeapon(timeWave);
        boss.addWeapon(timeWave);

        boss.setLevel(stage.getCurrentMission() * 2);
        boss.getHpPool().take(boss.getHpPool().getMaxAmount() * 0.67f);

        return boss;
    }
}
