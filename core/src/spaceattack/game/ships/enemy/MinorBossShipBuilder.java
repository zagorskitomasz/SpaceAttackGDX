package spaceattack.game.ships.enemy;

import spaceattack.consts.Sizes;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.ships.pools.Pool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.Acts;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.AIWeaponController;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.rocketMissile.RocketMissileBuilder;
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;

public enum MinorBossShipBuilder 
{
	INSTANCE;

	public IBoss build(Acts act, GameplayStage stage)
	{
		IBoss boss = new SuperBaseEnemyShip();
		buildShip(stage, boss);
		return boss;
	}

	private IEnemyShip buildShip(GameplayStage stage, IBoss boss)
	{
		MissilesLauncher launcher = stage.getMissilesLauncher();
		IWeaponController controller = new AIWeaponController();
		IWeapon rocketLauncher = RocketMissileBuilder.INSTANCE.build(controller, launcher);
		IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher);
		IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);
		Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();

		Burner burner = BurnerBuilder.INSTANCE.build(boss);

		IPool energyPool = new Pool(50, 10, 20, 5);
		IPool hpPool = new HpPool(200, 50, 0, 0);

		controller.setPrimaryWeapon(targetedRedLaser);
		controller.setSecondaryWeapon(rocketLauncher);
		controller.setShip(boss);

		boss.setActor(Factories.getActorFactory().create(boss));
		boss.setTexture(Textures.TANK1.getTexture());
		boss.setShipEngine(engine);
		boss.addWeapon(rocketLauncher);
		boss.addWeapon(targetedRedLaser);
		boss.setEnergyPool(energyPool);
		boss.setHpPool(hpPool);
		boss.setLevel(stage.getCurrentMission() * 2);
		boss.setWeaponController(controller);
		boss.setMissilesLauncher(launcher);
		boss.setExplosion(explosion);
		boss.setBar(new BigEnemyBar(boss));
		boss.setBurner(burner);
		boss.setX(Sizes.GAME_WIDTH * 0.2f + (float)Math.random() * Sizes.GAME_WIDTH * 0.6f);

		return boss;
	}
}
