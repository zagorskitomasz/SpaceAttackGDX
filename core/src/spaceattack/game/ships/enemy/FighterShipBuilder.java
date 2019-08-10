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
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.multiRedLaser.DoubleRedLaserBuilder;
import spaceattack.game.weapons.redLaser.RedLaserBuilder;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;
import spaceattack.game.weapons.tripleGreenLaser.TripleGreenLaserBuilder;

public enum FighterShipBuilder {
    INSTANCE;

    public IEnemyShip buildActI(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.FIGHTER1.getTexture());
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(redLaser);
        controller.setShip(ship);

        ship.addWeapon(redLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildActII(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.FIGHTER2.getTexture());
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(redLaser);
        controller.setShip(ship);

        ship.addWeapon(redLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildActIII(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.FIGHTER3.getTexture());
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon doubleRedLaser = DoubleRedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(doubleRedLaser);
        controller.setSecondaryWeapon(doubleRedLaser);
        controller.setShip(ship);

        ship.addWeapon(doubleRedLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildActIV(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.FIGHTER4.getTexture());
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon doubleRedLaser = DoubleRedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon tripleGreenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(doubleRedLaser);
        controller.setSecondaryWeapon(tripleGreenLaser);
        controller.setShip(ship);

        ship.addWeapon(doubleRedLaser);
        ship.addWeapon(tripleGreenLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    public IEnemyShip buildActV(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.FIGHTER5.getTexture());
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));
        IWeapon tripleGreenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher,
                ship.getAttributes().get(Attribute.ARMORY));

        controller.setPrimaryWeapon(targetedRedLaser);
        controller.setSecondaryWeapon(tripleGreenLaser);
        controller.setShip(ship);

        ship.addWeapon(targetedRedLaser);
        ship.addWeapon(tripleGreenLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);

        return ship;
    }

    private IEnemyShip build(final GameplayStage stage) {

        IEnemyShip fighter = new BaseEnemyShip(Consts.AttributesStarters.FIGHTER.get(stage.getCurrentMission()));
        IEngine engine = ShipEngineBuilder.INSTANCE.createFighterEngine(fighter);
        Explosion explosion = ExplosionsBuilder.INSTANCE.createFighterExplosion(stage);

        Burner burner = BurnerBuilder.INSTANCE.build(fighter);

        IPool energyPool = new Pool(fighter.getAttributes().get(Attribute.BATTERY));
        IPool hpPool = new HpPool(fighter.getAttributes().get(Attribute.SHIELDS));

        fighter.setActor(Factories.getActorFactory().create(fighter));
        fighter.setShipEngine(engine);
        fighter.setEnergyPool(energyPool);
        fighter.setHpPool(hpPool);
        fighter.setExplosion(explosion);
        fighter.setBar(new EnemyBar(fighter));
        fighter.setBurner(burner);

        return fighter;
    }
}
