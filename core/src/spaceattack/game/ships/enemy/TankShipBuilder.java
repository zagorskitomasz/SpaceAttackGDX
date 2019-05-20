package spaceattack.game.ships.enemy;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
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
import spaceattack.game.weapons.miner.MinerBuilder;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.redLaser.RedLaserBuilder;
import spaceattack.game.weapons.rocketMissile.RocketMissileBuilder;
import spaceattack.game.weapons.shield.ShieldBuilder;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;

public enum TankShipBuilder {
    INSTANCE;

    public IEnemyShip buildActI(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required ? new SuperBaseEnemyShip() : new BaseEnemyShip();
        buildShipActI(stage, tank);
        tank.setLevel(stage.getCurrentMission() * 2);

        return tank;
    }

    public IEnemyShip buildActII(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required ? new SuperBaseEnemyShip() : new BaseEnemyShip();
        buildShipActII(stage, tank);
        tank.setLevel(stage.getCurrentMission() * 2);

        return tank;
    }

    public IEnemyShip buildActIII(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required ? new SuperBaseEnemyShip() : new BaseEnemyShip();
        buildShipActIII(stage, tank);
        tank.setLevel(stage.getCurrentMission() * 2);

        return tank;
    }

    public IEnemyShip buildActIV(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required ? new SuperBaseEnemyShip() : new BaseEnemyShip();
        buildShipActIV(stage, tank);
        tank.setLevel(stage.getCurrentMission() * 2);

        return tank;
    }

    public IEnemyShip buildActV(final GameplayStage stage, final boolean required) {

        IEnemyShip tank = required ? new SuperBaseEnemyShip() : new BaseEnemyShip();
        buildShipActV(stage, tank);
        tank.setLevel(stage.getCurrentMission() * 2);

        return tank;
    }

    private IEnemyShip buildShipActI(final GameplayStage stage, final IEnemyShip tank) {

        IEnemyShip ship = buildShip(stage, tank);
        ship.setTexture(Textures.TANK1.getTexture());

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon rocketLauncher = RocketMissileBuilder.INSTANCE.build(controller, launcher);
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);

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
        IWeapon rocketLauncher = RocketMissileBuilder.INSTANCE.build(controller, launcher);
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher);

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
        IWeapon flyingMiner = FlyingMinerBuilder.INSTANCE.buildDelayed(controller, launcher);
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher);

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
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher);
        IWeapon shieldEmitter = ShieldBuilder.INSTANCE.build(controller, launcher);

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
        IWeapon miner = MinerBuilder.INSTANCE.build(controller, launcher);
        IWeapon shieldEmitter = ShieldBuilder.INSTANCE.build(controller, launcher);
        miner.setInterval(0.33f);

        controller.setPrimaryWeapon(miner);
        controller.setSecondaryWeapon(shieldEmitter);
        controller.setShip(ship);
        ship.addWeapon(shieldEmitter);
        ship.addWeapon(miner);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    private IEnemyShip buildShip(final GameplayStage stage, final IEnemyShip tank) {

        IEngine engine = ShipEngineBuilder.INSTANCE.createFighterEngine(tank);
        engine.setBaseSpeed(1f * Sizes.RADIUS_FACTOR);
        Explosion explosion = ExplosionsBuilder.INSTANCE.createTankExplosion(stage);

        Burner burner = BurnerBuilder.INSTANCE.build(tank);

        IPool energyPool = new Pool(
                Consts.Pools.TANK_ENERGY_BASE_AMOUNT,
                Consts.Pools.TANK_ENERGY_INCREASE_PER_LEVEL,
                Consts.Pools.TANK_ENERGY_BASE_REGEN,
                Consts.Pools.TANK_ENERGY_REGEN_PER_LEVEL);
        IPool hpPool = new HpPool(
                Consts.Pools.TANK_HP_BASE_AMOUNT,
                Consts.Pools.TANK_HP_INCREASE_PER_LEVEL,
                Consts.Pools.TANK_HP_BASE_REGEN,
                Consts.Pools.TANK_HP_REGEN_PER_LEVEL);

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
