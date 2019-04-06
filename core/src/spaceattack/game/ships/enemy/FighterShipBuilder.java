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
import spaceattack.game.weapons.redLaser.RedLaserBuilder;

public enum FighterShipBuilder
{
	INSTANCE;

	public IEnemyShip buildActI(GameplayStage stage)
	{
		IEnemyShip ship = build(stage);
		ship.setTexture(Textures.FIGHTER1.getTexture());
		return ship;
	}

	public IEnemyShip buildActII(GameplayStage stage)
	{
		IEnemyShip ship = build(stage);
		ship.setTexture(Textures.FIGHTER2.getTexture());
		return ship;
	}

	private IEnemyShip build(GameplayStage stage) 
	{
		IEnemyShip fighter = new BaseEnemyShip();
		MissilesLauncher launcher = stage.getMissilesLauncher();
		IWeaponController controller = new AIWeaponController();
		IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);
		IEngine engine = ShipEngineBuilder.INSTANCE.createFighterEngine(fighter);
		Explosion explosion = ExplosionsBuilder.INSTANCE.createFighterExplosion(stage);

		Burner burner = BurnerBuilder.INSTANCE.build(fighter);

		IPool energyPool = new Pool(
				Consts.POOLS.FIGHTER_ENERGY_BASE_AMOUNT, 
				Consts.POOLS.FIGHTER_ENERGY_INCREASE_PER_LEVEL, 
				Consts.POOLS.FIGHTER_ENERGY_BASE_REGEN,
				Consts.POOLS.FIGHTER_ENERGY_REGEN_PER_LEVEL);
		IPool hpPool = new HpPool(
				Consts.POOLS.FIGHTER_HP_BASE_AMOUNT, 
				Consts.POOLS.FIGHTER_HP_INCREASE_PER_LEVEL, 
				Consts.POOLS.FIGHTER_HP_BASE_REGEN,
				Consts.POOLS.FIGHTER_HP_REGEN_PER_LEVEL);

		controller.setPrimaryWeapon(redLaser);
		controller.setSecondaryWeapon(redLaser);
		controller.setShip(fighter);

		fighter.setActor(Factories.getActorFactory().create(fighter));
		fighter.setShipEngine(engine);
		fighter.addWeapon(redLaser);
		fighter.setEnergyPool(energyPool);
		fighter.setHpPool(hpPool);
		fighter.setLevel(stage.getCurrentMission() * 2);
		fighter.setWeaponController(controller);
		fighter.setMissilesLauncher(launcher);
		fighter.setExplosion(explosion);
		fighter.setBar(new EnemyBar(fighter));
		fighter.setBurner(burner);

		return fighter;
	}
}
