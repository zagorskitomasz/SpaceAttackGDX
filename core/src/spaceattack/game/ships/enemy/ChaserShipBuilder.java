package spaceattack.game.ships.enemy;

import spaceattack.consts.Consts;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Attribute;
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
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon greenLaser = GreenLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(greenLaser);
        controller.setShip(ship);
        ship.addWeapon(redLaser);
        ship.addWeapon(greenLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildActII(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER2.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon tripleGreenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(tripleGreenLaser);
        controller.setShip(ship);
        ship.addWeapon(redLaser);
        ship.addWeapon(tripleGreenLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildActIII(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER3.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon tripleGreenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(tripleGreenLaser);
        controller.setShip(ship);
        ship.addWeapon(redLaser);
        ship.addWeapon(tripleGreenLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildActIV(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER4.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon miner = FlyingMinerBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(miner);
        controller.setShip(ship);
        ship.addWeapon(redLaser);
        ship.addWeapon(miner);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildActV(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER5.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon flamethrower = FlamethrowerBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        flamethrower.setNoEnergyCost();

        controller.setPrimaryWeapon(targetedRedLaser);
        controller.setSecondaryWeapon(flamethrower);
        controller.setShip(ship);
        ship.addWeapon(targetedRedLaser);
        ship.addWeapon(flamethrower);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildSuperActIV(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER4.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon targetdRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(targetdRedLaser);
        controller.setSecondaryWeapon(targetdRedLaser);
        controller.setShip(ship);
        ship.addWeapon(targetdRedLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildSuperActV(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.CHASER5.getTexture());

        IWeaponController controller = new AIWeaponController();
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeapon targetdRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(targetdRedLaser);
        controller.setSecondaryWeapon(targetdRedLaser);
        controller.setShip(ship);
        ship.addWeapon(targetdRedLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    private IEnemyShip build(final GameplayStage stage) {

        IEnemyShip chaser = new BaseEnemyShip(Consts.AttributesStarters.CHASER.get(stage.getCurrentMission()));
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(chaser);
        Explosion explosion = ExplosionsBuilder.INSTANCE.createFighterExplosion(stage);

        Burner burner = BurnerBuilder.INSTANCE.build(chaser);

        IPool energyPool = new Pool(chaser.getAttributes().get(Attribute.BATTERY));
        IPool hpPool = new HpPool(chaser.getAttributes().get(Attribute.SHIELDS));

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
