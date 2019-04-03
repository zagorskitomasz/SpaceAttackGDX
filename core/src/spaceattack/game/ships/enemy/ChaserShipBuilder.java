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
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.redLaser.RedLaserBuilder;

public enum ChaserShipBuilder 
{
	INSTANCE;

	public IEnemyShip buildActI(GameplayStage stage)
	{
		return build(stage);
	}

	private IEnemyShip build(GameplayStage stage) 
	{
		IEnemyShip chaser = new BaseEnemyShip();
		MissilesLauncher launcher = stage.getMissilesLauncher();
		IWeaponController controller = new AIWeaponController();
		IWeapon greenLaser = GreenLaserBuilder.INSTANCE.build(controller, launcher);
		IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);
		IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(chaser);
		Explosion explosion = ExplosionsBuilder.INSTANCE.createFighterExplosion(stage);

		Burner burner = BurnerBuilder.INSTANCE.build(chaser);

		IPool energyPool = new Pool(
				Consts.POOLS.CHASER_ENERGY_BASE_AMOUNT, 
				Consts.POOLS.CHASER_ENERGY_INCREASE_PER_LEVEL, 
				Consts.POOLS.CHASER_ENERGY_BASE_REGEN,
				Consts.POOLS.CHASER_ENERGY_REGEN_PER_LEVEL);
		IPool hpPool = new HpPool(
				Consts.POOLS.CHASER_HP_BASE_AMOUNT, 
				Consts.POOLS.CHASER_HP_INCREASE_PER_LEVEL, 
				Consts.POOLS.CHASER_HP_BASE_REGEN,
				Consts.POOLS.CHASER_HP_REGEN_PER_LEVEL);

		controller.setPrimaryWeapon(redLaser);
		controller.setSecondaryWeapon(greenLaser);
		controller.setShip(chaser);

		chaser.setActor(Factories.getActorFactory().create(chaser));
		chaser.setTexture(Textures.CHASER1.getTexture());
		chaser.setShipEngine(engine);
		chaser.addWeapon(redLaser);
		chaser.addWeapon(greenLaser);
		chaser.setEnergyPool(energyPool);
		chaser.setHpPool(hpPool);
		chaser.setLevel(stage.getCurrentMission() * 2);
		chaser.setWeaponController(controller);
		chaser.setMissilesLauncher(launcher);
		chaser.setExplosion(explosion);
		chaser.setBar(new EnemyBar(chaser));
		chaser.setBurner(burner);

		return chaser;
	}
}
