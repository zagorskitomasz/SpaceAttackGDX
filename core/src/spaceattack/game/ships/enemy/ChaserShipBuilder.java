package spaceattack.game.ships.enemy;

import spaceattack.consts.Consts;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.ships.pools.Pool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.AIWeaponController;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.greenLaser.GreenLaserBuilder;
import spaceattack.game.weapons.miner.FlyingMinerBuilder;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.redLaser.RedLaserBuilder;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;
import spaceattack.game.weapons.tripleGreenLaser.TripleGreenLaserBuilder;

public enum ChaserShipBuilder {
    INSTANCE;

    public IEnemyShip buildActI(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER1.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);
        IWeapon greenLaser = GreenLaserBuilder.INSTANCE.build(controller, launcher);

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(greenLaser);
        controller.setShip(ship);
        ship.addWeapon(redLaser);
        ship.addWeapon(greenLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);
        ship.setLevel(stage.getCurrentMission() * 2);

        return ship;
    }

    public IEnemyShip buildActII(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER2.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);
        IWeapon tripleGreenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher);

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(tripleGreenLaser);
        controller.setShip(ship);
        ship.addWeapon(redLaser);
        ship.addWeapon(tripleGreenLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);
        ship.setLevel(stage.getCurrentMission() * 2);

        return ship;
    }

    public IEnemyShip buildActIII(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER3.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);
        IWeapon tripleGreenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher);

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(tripleGreenLaser);
        controller.setShip(ship);
        ship.addWeapon(redLaser);
        ship.addWeapon(tripleGreenLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);
        ship.setLevel(stage.getCurrentMission() * 2);

        return ship;
    }

    public IEnemyShip buildActIV(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER4.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);
        IWeapon miner = FlyingMinerBuilder.INSTANCE.build(controller, launcher);

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(miner);
        controller.setShip(ship);
        ship.addWeapon(redLaser);
        ship.addWeapon(miner);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);
        ship.setLevel(stage.getCurrentMission() * 2);

        return ship;
    }

    public IEnemyShip buildSuperActIV(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER4.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon targetdRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher);

        controller.setPrimaryWeapon(targetdRedLaser);
        controller.setSecondaryWeapon(targetdRedLaser);
        controller.setShip(ship);
        ship.addWeapon(targetdRedLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);
        ship.setLevel(stage.getCurrentMission() * 2);

        return ship;
    }

    private IEnemyShip build(final GameplayStage stage) {

        IEnemyShip chaser = new BaseEnemyShip();
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(chaser);
        Explosion explosion = ExplosionsBuilder.INSTANCE.createFighterExplosion(stage);

        Burner burner = BurnerBuilder.INSTANCE.build(chaser);

        IPool energyPool = new Pool(
                Consts.Pools.CHASER_ENERGY_BASE_AMOUNT,
                Consts.Pools.CHASER_ENERGY_INCREASE_PER_LEVEL,
                Consts.Pools.CHASER_ENERGY_BASE_REGEN,
                Consts.Pools.CHASER_ENERGY_REGEN_PER_LEVEL);
        IPool hpPool = new HpPool(
                Consts.Pools.CHASER_HP_BASE_AMOUNT,
                Consts.Pools.CHASER_HP_INCREASE_PER_LEVEL,
                Consts.Pools.CHASER_HP_BASE_REGEN,
                Consts.Pools.CHASER_HP_REGEN_PER_LEVEL);

        chaser.setActor(Factories.getActorFactory().create(chaser));
        chaser.setShipEngine(engine);
        chaser.setEnergyPool(energyPool);
        chaser.setHpPool(hpPool);
        chaser.setExplosion(explosion);
        chaser.setBar(new EnemyBar(chaser));
        chaser.setBurner(burner);

        return chaser;
    }
}
