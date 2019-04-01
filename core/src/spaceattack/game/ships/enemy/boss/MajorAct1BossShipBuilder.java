package spaceattack.game.ships.enemy.boss;

import spaceattack.consts.Sizes;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IBoss;
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
import spaceattack.game.weapons.targetedRedLaser.TargetedRedLaserBuilder;
import spaceattack.game.weapons.tripleGreenLaser.TripleGreenLaserBuilder;

public enum MajorAct1BossShipBuilder 
{
	INSTANCE;

	public IBoss build(GameplayStage stage)
	{
		IBoss boss = new Act1MajorBossShip();
		buildShip(stage, boss);
		return boss;
	}

	private IEnemyShip buildShip(GameplayStage stage, IBoss boss)
	{
		MissilesLauncher launcher = stage.getMissilesLauncher();
		IWeaponController controller = new AIWeaponController();
		IWeapon targetedRedLaser = TargetedRedLaserBuilder.INSTANCE.build(controller, launcher);
		IWeapon tripleGreenLaser = TripleGreenLaserBuilder.INSTANCE.build(controller, launcher);
		IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(boss);
		Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();

		Burner burner = BurnerBuilder.INSTANCE.build(boss);

		IPool energyPool = new Pool(100, 20, 40, 10);
		IPool hpPool = new HpPool(400, 100, 0, 0);

		controller.setPrimaryWeapon(targetedRedLaser);
		controller.setSecondaryWeapon(tripleGreenLaser);
		controller.setShip(boss);

		boss.setActor(Factories.getActorFactory().create(boss));
		boss.setTexture(Textures.BOSS1.getTexture());
		boss.setShipEngine(engine);
		boss.addWeapon(targetedRedLaser);
		boss.addWeapon(tripleGreenLaser);
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
