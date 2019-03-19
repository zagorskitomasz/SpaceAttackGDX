package spaceattack.game.ships.enemy;

import spaceattack.consts.Sizes;
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
import spaceattack.game.weapons.rocketMissile.RocketMissileBuilder;

public enum TankShipBuilder 
{
	INSTANCE;

	public IEnemyShip build(GameplayStage stage)
	{
		IEnemyShip tank = new BaseEnemyShip();
		buildShip(stage, tank);
		return tank;
	}

	public IEnemyShip buildRequired(GameplayStage stage) 
	{
		IEnemyShip tank = new SuperBaseEnemyShip();
		buildShip(stage, tank);
		return tank;
	}

	private IEnemyShip buildShip(GameplayStage stage, IEnemyShip tank)
	{
		MissilesLauncher launcher = stage.getMissilesLauncher();
		IWeaponController controller = new AIWeaponController();
		IWeapon rocketLauncher = RocketMissileBuilder.INSTANCE.build(controller, launcher);
		IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);
		IEngine engine = ShipEngineBuilder.INSTANCE.createFighterEngine(tank);
		engine.setBaseSpeed(1f * Sizes.RADIUS_FACTOR);
		Explosion explosion = ExplosionsBuilder.INSTANCE.createTankExplosion(stage);

		Burner burner = BurnerBuilder.INSTANCE.build(tank);

		IPool energyPool = new Pool(30, 15, 15, 3);
		IPool hpPool = new HpPool(80, 35, 5, 1);

		controller.setPrimaryWeapon(redLaser);
		controller.setSecondaryWeapon(rocketLauncher);
		controller.setShip(tank);

		tank.setActor(Factories.getActorFactory().create(tank));
		tank.setTexture(Textures.TANK1.getTexture());
		tank.setShipEngine(engine);
		tank.addWeapon(rocketLauncher);
		tank.addWeapon(redLaser);
		tank.setEnergyPool(energyPool);
		tank.setHpPool(hpPool);
		tank.setLevel(stage.getCurrentMission() * 2);
		tank.setWeaponController(controller);
		tank.setMissilesLauncher(launcher);
		tank.setExplosion(explosion);
		tank.setBar(new EnemyBar(tank));
		tank.setBurner(burner);
		tank.setX(Sizes.GAME_WIDTH * 0.2f + (float)Math.random() * Sizes.GAME_WIDTH * 0.6f);

		return tank;
	}
}
