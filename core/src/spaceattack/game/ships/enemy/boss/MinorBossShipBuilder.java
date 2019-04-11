package spaceattack.game.ships.enemy.boss;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.BigEnemyBar;
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
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;

public enum MinorBossShipBuilder 
{
	INSTANCE;

	public IBoss buildActI(GameplayStage stage)
	{
		return build(stage);
	}

	private IBoss build(GameplayStage stage)
	{
		IBoss boss = new SuperBaseEnemyShip();
		MissilesLauncher launcher = stage.getMissilesLauncher();
		IWeaponController controller = new AIWeaponController();
		IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher);
		IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);
		Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();

		Burner burner = BurnerBuilder.INSTANCE.build(boss);

		IPool energyPool = new Pool(
				Consts.Pools.MINOR_BOSS_ENERGY_BASE_AMOUNT, 
				Consts.Pools.MINOR_BOSS_ENERGY_INCREASE_PER_LEVEL, 
				Consts.Pools.MINOR_BOSS_ENERGY_BASE_REGEN,
				Consts.Pools.MINOR_BOSS_ENERGY_REGEN_PER_LEVEL);
		IPool hpPool = new HpPool(
				Consts.Pools.MINOR_BOSS_HP_BASE_AMOUNT, 
				Consts.Pools.MINOR_BOSS_HP_INCREASE_PER_LEVEL, 
				Consts.Pools.MINOR_BOSS_HP_BASE_REGEN,
				Consts.Pools.MINOR_BOSS_HP_REGEN_PER_LEVEL);

		controller.setPrimaryWeapon(targetedRedLaser);
		controller.setSecondaryWeapon(targetedRedLaser);
		controller.setShip(boss);

		boss.setActor(Factories.getActorFactory().create(boss));
		boss.setTexture(Textures.TANK1.getTexture());
		boss.setShipEngine(engine);
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