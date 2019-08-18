package spaceattack.game.ships.enemy;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Attribute;
import spaceattack.game.rpg.Improvement;
import spaceattack.game.ships.enemy.boss.SuperBaseEnemyShip;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.ships.pools.Pool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.AIWeaponController;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.miner.FlyingMinerBuilder;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.redLaser.RedLaserBuilder;
import spaceattack.game.weapons.rocketMissile.RocketMissileBuilder;
import spaceattack.game.weapons.shield.ShieldBuilder;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;
import spaceattack.game.weapons.timeWave.TimeWaveEmitterBuilder;

public enum TankShipBuilder {
    INSTANCE;

    public IEnemyShip buildActI(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required
                ? new SuperBaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()))
                : new BaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()),
                        stage.getGameProgress().getImprovements().get(Improvement.FEAR));
        buildShipActI(stage, tank);

        return tank;
    }

    public IEnemyShip buildActII(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required
                ? new SuperBaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()))
                : new BaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()),
                        stage.getGameProgress().getImprovements().get(Improvement.FEAR));
        buildShipActII(stage, tank);

        return tank;
    }

    public IEnemyShip buildActIII(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required
                ? new SuperBaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()))
                : new BaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()),
                        stage.getGameProgress().getImprovements().get(Improvement.FEAR));
        buildShipActIII(stage, tank);

        return tank;
    }

    public IEnemyShip buildActIV(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required
                ? new SuperBaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()))
                : new BaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()),
                        stage.getGameProgress().getImprovements().get(Improvement.FEAR));
        buildShipActIV(stage, tank);

        return tank;
    }

    public IEnemyShip buildActV(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required
                ? new SuperBaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()))
                : new BaseEnemyShip(Consts.AttributesStarters.TANK.get(stage.getCurrentMission()),
                        stage.getGameProgress().getImprovements().get(Improvement.FEAR));
        buildShipActV(stage, tank);

        return tank;
    }

    private IEnemyShip buildShipActI(final GameplayStage stage, final IEnemyShip tank) {

        IEnemyShip ship = buildShip(stage, tank);
        ship.setTexture(Textures.TANK1.getTexture());

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon rocketLauncher = RocketMissileBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(rocketLauncher);
        controller.setShip(ship);
        ship.addWeapon(rocketLauncher);
        ship.addWeapon(redLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    private IEnemyShip buildShipActII(final GameplayStage stage, final IEnemyShip tank) {

        IEnemyShip ship = buildShip(stage, tank);
        ship.setTexture(Textures.TANK2.getTexture());

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon rocketLauncher = RocketMissileBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(targetedRedLaser);
        controller.setSecondaryWeapon(rocketLauncher);
        controller.setShip(ship);
        ship.addWeapon(rocketLauncher);
        ship.addWeapon(targetedRedLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    private IEnemyShip buildShipActIII(final GameplayStage stage, final IEnemyShip tank) {

        IEnemyShip ship = buildShip(stage, tank);
        ship.setTexture(Textures.TANK3.getTexture());

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon flyingMiner = FlyingMinerBuilder.INSTANCE.buildDelayed(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(targetedRedLaser);
        controller.setSecondaryWeapon(flyingMiner);
        controller.setShip(ship);
        ship.addWeapon(flyingMiner);
        ship.addWeapon(targetedRedLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    private IEnemyShip buildShipActIV(final GameplayStage stage, final IEnemyShip tank) {

        IEnemyShip ship = buildShip(stage, tank);
        ship.setTexture(Textures.TANK4.getTexture());

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon shieldEmitter = ShieldBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(targetedRedLaser);
        controller.setSecondaryWeapon(shieldEmitter);
        controller.setShip(ship);
        ship.addWeapon(shieldEmitter);
        ship.addWeapon(targetedRedLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    private IEnemyShip buildShipActV(final GameplayStage stage, final IEnemyShip tank) {

        IEnemyShip ship = buildShip(stage, tank);
        ship.setTexture(Textures.TANK5.getTexture());

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon timeWaveEmitter = TimeWaveEmitterBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon shieldEmitter = ShieldBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        timeWaveEmitter.setInterval(0.33f);
        timeWaveEmitter.setNoEnergyCost();

        controller.setPrimaryWeapon(timeWaveEmitter);
        controller.setSecondaryWeapon(shieldEmitter);
        controller.setShip(ship);
        ship.addWeapon(shieldEmitter);
        ship.addWeapon(timeWaveEmitter);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    private IEnemyShip buildShip(final GameplayStage stage, final IEnemyShip tank) {

        IEngine engine = ShipEngineBuilder.INSTANCE.createFighterEngine(tank);
        Explosion explosion = ExplosionsBuilder.INSTANCE.createTankExplosion(stage);

        Burner burner = BurnerBuilder.INSTANCE.build(tank);

        IPool energyPool = new Pool(tank.getAttributes().get(Attribute.BATTERY));
        IPool hpPool = new HpPool(tank.getAttributes().get(Attribute.SHIELDS));

        tank.setActor(Factories.getActorFactory().create(tank));
        tank.setShipEngine(engine);
        tank.setEnergyPool(energyPool);
        tank.setHpPool(hpPool);
        tank.setExplosion(explosion);
        tank.setBar(new EnemyBar(tank));
        tank.setBurner(burner);
        tank.setX(Sizes.GAME_WIDTH * 0.2f + (float) Math.random() * Sizes.GAME_WIDTH * 0.6f);

        return tank;
    }
}
