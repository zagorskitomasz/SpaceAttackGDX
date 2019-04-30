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
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.multiRedLaser.DoubleRedLaserBuilder;
import spaceattack.game.weapons.redLaser.RedLaserBuilder;

public enum FighterShipBuilder {
    INSTANCE;

    public IEnemyShip buildActI(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.FIGHTER1.getTexture());
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(redLaser);
        controller.setShip(ship);

        ship.addWeapon(redLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);
        ship.setLevel(stage.getCurrentMission() * 2);

        return ship;
    }

    public IEnemyShip buildActII(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.FIGHTER2.getTexture());
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);

        controller.setPrimaryWeapon(redLaser);
        controller.setSecondaryWeapon(redLaser);
        controller.setShip(ship);

        ship.addWeapon(redLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);
        ship.setLevel(stage.getCurrentMission() * 2);

        return ship;
    }

    public IEnemyShip buildActIII(final GameplayStage stage) {

        IEnemyShip ship = build(stage);
        ship.setTexture(Textures.FIGHTER3.getTexture());
        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon doubleRedLaser = DoubleRedLaserBuilder.INSTANCE.build(controller, launcher);

        controller.setPrimaryWeapon(doubleRedLaser);
        controller.setSecondaryWeapon(doubleRedLaser);
        controller.setShip(ship);

        ship.addWeapon(doubleRedLaser);
        ship.setWeaponController(controller);
        ship.setMissilesLauncher(launcher);
        ship.setLevel(stage.getCurrentMission() * 2);

        return ship;
    }

    private IEnemyShip build(final GameplayStage stage) {

        IEnemyShip fighter = new BaseEnemyShip();
        IEngine engine = ShipEngineBuilder.INSTANCE.createFighterEngine(fighter);
        Explosion explosion = ExplosionsBuilder.INSTANCE.createFighterExplosion(stage);

        Burner burner = BurnerBuilder.INSTANCE.build(fighter);

        IPool energyPool = new Pool(
                Consts.Pools.FIGHTER_ENERGY_BASE_AMOUNT,
                Consts.Pools.FIGHTER_ENERGY_INCREASE_PER_LEVEL,
                Consts.Pools.FIGHTER_ENERGY_BASE_REGEN,
                Consts.Pools.FIGHTER_ENERGY_REGEN_PER_LEVEL);
        IPool hpPool = new HpPool(
                Consts.Pools.FIGHTER_HP_BASE_AMOUNT,
                Consts.Pools.FIGHTER_HP_INCREASE_PER_LEVEL,
                Consts.Pools.FIGHTER_HP_BASE_REGEN,
                Consts.Pools.FIGHTER_HP_REGEN_PER_LEVEL);

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
