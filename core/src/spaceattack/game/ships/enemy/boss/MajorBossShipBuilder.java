package spaceattack.game.ships.enemy.boss;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
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
import spaceattack.game.weapons.flame.FlamethrowerBuilder;
import spaceattack.game.weapons.miner.FlyingMiner;
import spaceattack.game.weapons.miner.FlyingMinerBuilder;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.multiRedLaser.DistractedRedLaserBuilder;
import spaceattack.game.weapons.rocketMissile.RocketMissileBuilder;
import spaceattack.game.weapons.shield.ShieldBuilder;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;
import spaceattack.game.weapons.timeWave.TimeWaveEmitterBuilder;
import spaceattack.game.weapons.tripleGreenLaser.TripleGreenLaserBuilder;

public enum MajorBossShipBuilder {
    INSTANCE;

    public IBoss buildActI(final GameplayStage stage) {

        IBoss boss = build(stage);

        boss.setDefaultMoverType(MoverType.ALL_CORNERS_CHASER);
        boss.setDefaultShooterType(ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher);
        IWeapon tripleGreenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher);
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);

        IPool hpPool = new HpPool(
                Consts.Pools.MAJOR_BOSS_HP_BASE_AMOUNT,
                Consts.Pools.MAJOR_BOSS_HP_INCREASE_PER_LEVEL,
                Consts.Pools.MAJOR_BOSS_HP_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_HP_REGEN_PER_LEVEL);
        boss.setHpPool(hpPool);

        controller.setPrimaryWeapon(targetedRedLaser);
        controller.setSecondaryWeapon(tripleGreenLaser);
        controller.setShip(boss);
        boss.addWeapon(targetedRedLaser);
        boss.addWeapon(tripleGreenLaser);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);
        boss.setShipEngine(engine);
        boss.setTexture(Textures.BOSS1.getTexture());

        boss.setLevel(stage.getCurrentMission() * 2);

        return boss;
    }

    public IBoss buildActII(final GameplayStage stage) {

        IBoss boss = build(stage);

        boss.setDefaultMoverType(MoverType.CORNERS_CHASER);
        boss.setDefaultShooterType(ShooterType.NOTIFIED_SNIPER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon distractedRedLaser = DistractedRedLaserBuilder.INSTANCE.build(controller, launcher);
        IWeapon flyingMine = FlyingMinerBuilder.INSTANCE.build(controller, launcher);
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);

        IPool hpPool = new HpPool(
                Consts.Pools.MAJOR_BOSS_HP_BASE_AMOUNT,
                Consts.Pools.MAJOR_BOSS_HP_INCREASE_PER_LEVEL,
                Consts.Pools.MAJOR_BOSS_HP_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_HP_REGEN_PER_LEVEL);
        boss.setHpPool(hpPool);

        controller.setPrimaryWeapon(distractedRedLaser);
        controller.setSecondaryWeapon(flyingMine);
        controller.setShip(boss);
        boss.addWeapon(distractedRedLaser);
        boss.addWeapon(flyingMine);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);
        boss.setShipEngine(engine);
        boss.setTexture(Textures.BOSS2.getTexture());

        boss.setLevel(stage.getCurrentMission() * 2);

        return boss;
    }

    public IBoss buildActIII(final GameplayStage stage) {

        IBoss boss = build(stage);

        boss.setDefaultMoverType(MoverType.CORRECTABLE_FRONT_CHASER);
        boss.setDefaultShooterType(ShooterType.DIRECT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon rocketMissile = RocketMissileBuilder.INSTANCE.build(controller, launcher);
        IWeapon timeWaveEmitter = TimeWaveEmitterBuilder.INSTANCE.build(controller, launcher);
        IEngine engine = ShipEngineBuilder.INSTANCE.createFastDestinationEngine(boss);

        IPool hpPool = new HpPool(
                Consts.Pools.MAJOR_BOSS_HP_BASE_AMOUNT,
                Consts.Pools.MAJOR_BOSS_HP_INCREASE_PER_LEVEL,
                Consts.Pools.MAJOR_BOSS_HP_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_HP_REGEN_PER_LEVEL);
        boss.setHpPool(hpPool);

        controller.setPrimaryWeapon(rocketMissile);
        controller.setSecondaryWeapon(timeWaveEmitter);
        controller.setShip(boss);
        boss.addWeapon(rocketMissile);
        boss.addWeapon(timeWaveEmitter);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);
        boss.setShipEngine(engine);
        boss.setTexture(Textures.BOSS3.getTexture());

        boss.setLevel(stage.getCurrentMission() * 2);

        return boss;
    }

    public IBoss buildActIV(final GameplayStage stage) {

        IBoss boss = build(stage);

        boss.setDefaultMoverType(MoverType.CORRECTABLE_CLOSE_FRONT_CHASER);
        boss.setDefaultShooterType(ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon timeWaveEmitter = TimeWaveEmitterBuilder.INSTANCE.build(controller, launcher);
        IWeapon flamethrower = FlamethrowerBuilder.INSTANCE.build(controller, launcher);
        flamethrower.setInterval(0.2f);
        IEngine engine = ShipEngineBuilder.INSTANCE.createFastDestinationEngine(boss);

        IPool hpPool = new HpPool(
                Consts.Pools.MAJOR_BOSS_HP_BASE_AMOUNT * 3,
                Consts.Pools.MAJOR_BOSS_HP_INCREASE_PER_LEVEL * 3,
                Consts.Pools.MAJOR_BOSS_HP_BASE_REGEN * 2,
                Consts.Pools.MAJOR_BOSS_HP_REGEN_PER_LEVEL * 2);
        boss.setHpPool(hpPool);

        controller.setPrimaryWeapon(timeWaveEmitter);
        controller.setSecondaryWeapon(flamethrower);
        controller.setShip(boss);
        boss.addWeapon(timeWaveEmitter);
        boss.addWeapon(flamethrower);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);
        boss.setShipEngine(engine);
        boss.setTexture(Textures.BOSS4.getTexture());

        boss.setLevel(stage.getCurrentMission() * 2);

        return boss;
    }

    public IBoss buildActV(final GameplayStage stage) {

        IBoss boss = build(stage);

        boss.setDefaultMoverType(MoverType.CENTRAL_STATION);
        boss.setDefaultShooterType(ShooterType.INSTANT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        FlyingMiner miner = (FlyingMiner) FlyingMinerBuilder.INSTANCE.build(controller, launcher);
        miner.setInterval(0.2f);
        miner.setDistanceToShip(0.6f);
        IWeapon shield = ShieldBuilder.INSTANCE.build(controller, launcher);
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);

        IPool hpPool = new HpPool(
                Consts.Pools.MAJOR_BOSS_HP_BASE_AMOUNT,
                Consts.Pools.MAJOR_BOSS_HP_INCREASE_PER_LEVEL,
                Consts.Pools.MAJOR_BOSS_HP_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_HP_REGEN_PER_LEVEL);
        boss.setHpPool(hpPool);

        controller.setSecondaryWeapon(miner);
        controller.setPrimaryWeapon(shield);
        controller.setShip(boss);
        boss.addWeapon(miner);
        boss.addWeapon(shield);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);
        boss.setShipEngine(engine);
        boss.setTexture(Textures.BOSS5.getTexture());

        boss.setLevel(stage.getCurrentMission() * 2);

        return boss;
    }

    private IBoss build(final GameplayStage stage) {

        IBoss boss = new BossShip();
        Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();

        Burner burner = BurnerBuilder.INSTANCE.build(boss);

        IPool energyPool = new Pool(
                Consts.Pools.MAJOR_BOSS_ENERGY_BASE_AMOUNT,
                Consts.Pools.MAJOR_BOSS_ENERGY_INCREASE_PER_LEVEL,
                Consts.Pools.MAJOR_BOSS_ENERGY_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_ENERGY_REGEN_PER_LEVEL);

        boss.setActor(Factories.getActorFactory().create(boss));
        boss.setEnergyPool(energyPool);
        boss.setExplosion(explosion);
        boss.setBar(new BigEnemyBar(boss));
        boss.setBurner(burner);
        boss.setX(Sizes.GAME_WIDTH * 0.2f + (float) Math.random() * Sizes.GAME_WIDTH * 0.6f);

        return boss;
    }
}
