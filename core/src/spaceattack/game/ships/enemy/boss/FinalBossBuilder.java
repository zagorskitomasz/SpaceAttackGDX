package spaceattack.game.ships.enemy.boss;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.BaseEnemyShip;
import spaceattack.game.ships.enemy.BigEnemyBar;
import spaceattack.game.ships.enemy.IEnemyShip;
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
import spaceattack.game.weapons.shield.ShieldBuilder;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;

public enum FinalBossBuilder implements IFinalBossShipBuilder {
    INSTANCE;

    @Override
    public IBoss createSpaceStationI(final GameplayStage stage) {

        IBoss boss = new BossShip();
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
        IWeapon shield = ShieldBuilder.INSTANCE.build(controller, launcher);
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);

        controller.setSecondaryWeapon(shield);
        controller.setPrimaryWeapon(shield);
        controller.setShip(boss);
        boss.addWeapon(shield);
        boss.setWeaponController(controller);
        boss.setMissilesLauncher(launcher);
        boss.setShipEngine(engine);
        boss.setTexture(Textures.SPACE_STATION.getTexture());

        boss.setLevel(stage.getCurrentMission() * 2);

        return boss;
    }

    @Override
    public IEnemyShip createWeaponHolder(final GameplayStage stage) {

        IEnemyShip holder = new BaseEnemyShip();

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

        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setEnergyPool(energyPool);
        holder.setHpPool(hpPool);
        holder.setX(Sizes.GAME_WIDTH * 0.2f + (float) Math.random() * Sizes.GAME_WIDTH * 0.6f);

        MissilesLauncher launcher = stage.getMissilesLauncher();
        IWeaponController controller = new AIWeaponController();
        IWeapon targetedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher);
        targetedLaser.setNoEnergyCost();

        controller.setSecondaryWeapon(targetedLaser);
        controller.setPrimaryWeapon(targetedLaser);
        controller.setShip(holder);
        holder.addWeapon(targetedLaser);
        holder.setWeaponController(controller);
        holder.setMissilesLauncher(launcher);

        holder.setLevel(stage.getCurrentMission() * 2);

        return holder;
    }
}
