package com.zagorskidev.spaceattack.ships.player;

import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.moving.engines.ShipEngineBuilder;
import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.Pool;
import com.zagorskidev.spaceattack.stages.GameplayStage;
import com.zagorskidev.spaceattack.stages.impl.Bar;
import com.zagorskidev.spaceattack.stages.impl.EnergyBar;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.WeaponFactory;

public enum PlayerShipFactory
{
	INSTANCE;

	public IShip create(GameplayStage stage)
	{
		PlayerShip ship = new PlayerShip();

		//@formatter:off
		IEngine engine = ShipEngineBuilder
				.INSTANCE
				.getBuilder(ship)
				.setBaseSpeed(1f)
				.setAcceleration(1f)
				.setBraking(1f)
				.setAgility(2f)
				.build();

		IWeapon redLaser = WeaponFactory
				.INSTANCE
				.redLaser()
				.setController(stage)
				.setMissileLauncher(stage.getMissileLauncher())
				.setLevel(1)
				.build();
		//@formatter:on

		IPool energyPool = new Pool(50, 10, 10, 2);
		Bar energyBar = new EnergyBar(energyPool);

		stage.setPrimaryWeapon(redLaser);
		stage.addActor(energyBar);

		ship.loadComplexGraphics(Textures.PLAYER_SHIP_F.getTexture(), Textures.PLAYER_SHIP_R.getTexture(),
				Textures.PLAYER_SHIP_L.getTexture());
		ship.setShipEngine(engine);
		ship.addWeapon(redLaser);
		ship.setEnergyPool(energyPool);

		return ship;
	}
}
