package spaceattack.game.ships.enemy;

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
import spaceattack.game.weapons.redLaser.RedLaserBuilder;

public enum FighterShipBuilder
{
	INSTANCE;

	public IEnemyShip build(GameplayStage stage)
	{
		IEnemyShip fighter = new Fighter();
		MissilesLauncher launcher = stage.getMissilesLauncher();
		IWeaponController controller = new AIWeaponController();
		IWeapon redLaser = RedLaserBuilder.INSTANCE.build(controller, launcher);
		IEngine engine = ShipEngineBuilder.INSTANCE.createFighterEngine(fighter);

		IPool energyPool = new Pool(10, 10, 10, 2);
		IPool hpPool = new HpPool(50, 10, 5, 1);

		controller.setPrimaryWeapon(redLaser);
		controller.setSecondaryWeapon(redLaser);
		controller.setShip(fighter);

		fighter.setActor(Factories.getActorFactory().create(fighter));
		fighter.setTexture(Textures.FIGHTER1.getTexture());
		fighter.setShipEngine(engine);
		fighter.addWeapon(redLaser);
		fighter.setEnergyPool(energyPool);
		fighter.setHpPool(hpPool);
		fighter.setLevel(stage.getGameProgress().getMission() * 2);
		fighter.setWeaponController(controller);

		return fighter;
	}
}