package spaceattack.game.ships.enemy.boss;

import spaceattack.consts.Consts.AttributesStarters;
import spaceattack.consts.Consts.Starter;
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

        IBoss boss = build(stage, AttributesStarters.MAJOR_AI);

        boss.setDefaultMoverType(MoverType.ALL_CORNERS_CHASER);
        boss.setDefaultShooterType(ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IWeapon tripleGreenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);

        IPool hpPool = new HpPool(boss.getAttributes().get(Attribute.SHIELDS), 0.2f);
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

        return boss;
    }

    public IBoss buildActII(final GameplayStage stage) {

        IBoss boss = build(stage, AttributesStarters.MAJOR_AII);

        boss.setDefaultMoverType(MoverType.CORNERS_CHASER);
        boss.setDefaultShooterType(ShooterType.NOTIFIED_SNIPER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon distractedRedLaser = DistractedRedLaserBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IWeapon flyingMine = FlyingMinerBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);

        IPool hpPool = new HpPool(boss.getAttributes().get(Attribute.SHIELDS), 0.2f);
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

        return boss;
    }

    public IBoss buildActIII(final GameplayStage stage) {

        IBoss boss = build(stage, AttributesStarters.MAJOR_AIII);

        boss.setDefaultMoverType(MoverType.CORRECTABLE_FRONT_CHASER);
        boss.setDefaultShooterType(ShooterType.DIRECT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon rocketMissile = RocketMissileBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IWeapon timeWaveEmitter = TimeWaveEmitterBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IEngine engine = ShipEngineBuilder.INSTANCE.createFastDestinationEngine(boss);

        IPool hpPool = new HpPool(boss.getAttributes().get(Attribute.SHIELDS), 0.2f);
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

        return boss;
    }

    public IBoss buildActIV(final GameplayStage stage) {

        IBoss boss = build(stage, AttributesStarters.MAJOR_AIV);

        boss.setDefaultMoverType(MoverType.CORRECTABLE_CLOSE_FRONT_CHASER);
        boss.setDefaultShooterType(ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon timeWaveEmitter = TimeWaveEmitterBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        IWeapon flamethrower = FlamethrowerBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        flamethrower.setInterval(0.2f);
        IEngine engine = ShipEngineBuilder.INSTANCE.createFastDestinationEngine(boss);

        IPool hpPool = new HpPool(boss.getAttributes().get(Attribute.SHIELDS), 0.2f);
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

        return boss;
    }

    public IBoss buildActV(final GameplayStage stage) {

        IBoss boss = build(stage, AttributesStarters.MAJOR_AV);

        boss.setDefaultMoverType(MoverType.CENTRAL_STATION);
        boss.setDefaultShooterType(ShooterType.INSTANT_SHOOTER);
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        FlyingMiner miner = (FlyingMiner) FlyingMinerBuilder.INSTANCE.build(controller, launcher,
                boss.getAttributes().get(Attribute.ARMORY));
        miner.setInterval(0.2f);
        miner.setDistanceToShip(0.6f);
        IWeapon shield = ShieldBuilder.INSTANCE.build(controller, launcher, boss.getAttributes().get(Attribute.ARMORY));
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);

        IPool hpPool = new HpPool(boss.getAttributes().get(Attribute.SHIELDS), 0.2f);
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

        return boss;
    }

    private IBoss build(final GameplayStage stage, final Starter starter) {

        IBoss boss = new RequiredBossShip(starter.get(stage.getCurrentMission()));
        Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();

        Burner burner = BurnerBuilder.INSTANCE.build(boss);

        IPool energyPool = new Pool(boss.getAttributes().get(Attribute.BATTERY));

        boss.setActor(Factories.getActorFactory().create(boss));
        boss.setEnergyPool(energyPool);
        boss.setExplosion(explosion);
        boss.setBar(new BigEnemyBar(boss));
        boss.setBurner(burner);
        boss.setX(Sizes.GAME_WIDTH * 0.2f + (float) Math.random() * Sizes.GAME_WIDTH * 0.6f);

        return boss;
    }
}
