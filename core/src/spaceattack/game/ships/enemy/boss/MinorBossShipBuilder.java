package spaceattack.game.ships.enemy.boss;

import spaceattack.consts.Consts.AttributesStarters;
import spaceattack.consts.Sizes;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Attribute;
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
import spaceattack.game.weapons.miner.MinerBuilder;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.rocketMissile.DoubleRocketBuilder;
import spaceattack.game.weapons.shield.ShieldBuilder;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;
import spaceattack.game.weapons.timeWave.TimeWaveEmitterBuilder;

public enum MinorBossShipBuilder {
    INSTANCE;

    public IBoss buildActI(final GameplayStage stage) {

        IBoss boss = new RequiredBossShip(AttributesStarters.MINOR_AI.get(stage.getCurrentMission()));
        boss.setDefaultMoverType(MoverType.CORNERS_CHASER);
        boss.setDefaultShooterType(ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER);

        build(stage, boss);
        boss.setTexture(Textures.TANK1.getTexture());
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);
        boss.setShipEngine(engine);

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));

        IPool energyPool = createEnergyPool(boss.getAttributes().get(Attribute.BATTERY));

        controller.setPrimaryWeapon(targetedRedLaser);
        controller.setSecondaryWeapon(targetedRedLaser);
        controller.setShip(boss);

        boss.setEnergyPool(energyPool);
        boss.addWeapon(targetedRedLaser);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);

        return boss;
    }

    public IBoss buildActII(final GameplayStage stage) {

        IBoss boss = new RequiredBossShip(AttributesStarters.MINOR_AII.get(stage.getCurrentMission()));
        boss.setDefaultMoverType(MoverType.CORRECTABLE_JUMPER);
        boss.setDefaultShooterType(ShooterType.NOTIFIED_SNIPER);

        build(stage, boss);
        boss.setTexture(Textures.TANK2.getTexture());
        IEngine engine = ShipEngineBuilder.INSTANCE.createFastDestinationEngine(boss);
        boss.setShipEngine(engine);

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IWeapon miner = MinerBuilder.INSTANCE.build(controller, launcher, boss.getAttributes().get(Attribute.ARMORY));

        IPool energyPool = createEnergyPool(boss.getAttributes().get(Attribute.BATTERY));

        controller.setPrimaryWeapon(targetedRedLaser);
        controller.setSecondaryWeapon(miner);
        controller.setShip(boss);

        boss.setEnergyPool(energyPool);
        boss.addWeapon(targetedRedLaser);
        boss.addWeapon(miner);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);

        return boss;
    }

    public IBoss buildActIII(final GameplayStage stage) {

        IBoss boss = new RequiredBossShip(AttributesStarters.MINOR_AIII.get(stage.getCurrentMission()));
        boss.setDefaultMoverType(MoverType.DIRECT_CHASER);
        boss.setDefaultShooterType(ShooterType.INSTANT_SHOOTER);

        build(stage, boss);
        boss.setTexture(Textures.TANK3.getTexture());
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);
        boss.setShipEngine(engine);

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IWeapon shieldEmiter = ShieldBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));

        IPool energyPool = createEnergyPool(boss.getAttributes().get(Attribute.BATTERY));

        controller.setPrimaryWeapon(targetedRedLaser);
        controller.setSecondaryWeapon(shieldEmiter);
        controller.setShip(boss);

        boss.setEnergyPool(energyPool);
        boss.addWeapon(targetedRedLaser);
        boss.addWeapon(shieldEmiter);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);

        return boss;
    }

    public IBoss buildActIV(final GameplayStage stage) {

        IBoss boss = new RequiredBossShip(AttributesStarters.MINOR_AIV.get(stage.getCurrentMission()));
        boss.setDefaultMoverType(MoverType.CORRECTABLE_JUMPER);
        boss.setDefaultShooterType(ShooterType.NOTIFIED_SNIPER);

        build(stage, boss);
        boss.setTexture(Textures.TANK4.getTexture());
        IEngine engine = ShipEngineBuilder.INSTANCE.createFastDestinationEngine(boss);
        boss.setShipEngine(engine);

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon shieldEmitter = ShieldBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY) * 3);
        IWeapon timeWaveEmitter = TimeWaveEmitterBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));

        timeWaveEmitter.setNoEnergyCost();

        IPool energyPool = createEnergyPool(boss.getAttributes().get(Attribute.BATTERY));

        controller.setPrimaryWeapon(shieldEmitter);
        controller.setSecondaryWeapon(timeWaveEmitter);
        controller.setShip(boss);

        boss.setEnergyPool(energyPool);
        boss.addWeapon(shieldEmitter);
        boss.addWeapon(timeWaveEmitter);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);

        return boss;
    }

    public IBoss buildActV(final GameplayStage stage) {

        IBoss boss = new RequiredBossShip(AttributesStarters.MINOR_AV.get(stage.getCurrentMission()));
        boss.setDefaultMoverType(MoverType.CORNERS_CHASER);
        boss.setDefaultShooterType(ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER);

        build(stage, boss);
        boss.setTexture(Textures.TANK4.getTexture());
        IEngine engine = ShipEngineBuilder.INSTANCE.createFastDestinationEngine(boss);
        boss.setShipEngine(engine);

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon timeWaveEmitter = TimeWaveEmitterBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IWeapon doubleRocket = DoubleRocketBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));

        timeWaveEmitter.setInterval(0.20f);
        doubleRocket.setInterval(0.33f);

        IPool energyPool = createEnergyPool(boss.getAttributes().get(Attribute.BATTERY));

        controller.setPrimaryWeapon(timeWaveEmitter);
        controller.setSecondaryWeapon(doubleRocket);
        controller.setShip(boss);

        boss.setEnergyPool(energyPool);
        boss.addWeapon(doubleRocket);
        boss.addWeapon(timeWaveEmitter);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);

        return boss;
    }

    private IBoss build(final GameplayStage stage, final IBoss boss) {

        Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();

        Burner burner = BurnerBuilder.INSTANCE.build(boss);

        IPool hpPool = new HpPool(boss.getAttributes().get(Attribute.SHIELDS), 0.2f);

        boss.setActor(Factories.getActorFactory().create(boss));
        boss.setHpPool(hpPool);
        boss.setExplosion(explosion);
        boss.setBar(new BigEnemyBar(boss));
        boss.setBurner(burner);
        boss.setX(Sizes.GAME_WIDTH * 0.2f + (float) Math.random() * Sizes.GAME_WIDTH * 0.6f);

        return boss;
    }

    private IPool createEnergyPool(final int armory) {

        IPool energyPool = new Pool(armory);

        return energyPool;
    }
}
