package com.zagorskidev.spaceattack.ships.player;

import com.zagorskidev.spaceattack.moving.engines.ShipEngineBuilder;
import com.zagorskidev.spaceattack.ships.HpPool;
import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.Pool;
import com.zagorskidev.spaceattack.stages.GameplayStageLegacy;
import com.zagorskidev.spaceattack.stages.impl.Bar;
import com.zagorskidev.spaceattack.stages.impl.HpEnergyBar;

import spaceattack.game.engines.IEngine;
import spaceattack.game.system.graphics.Textures;

public enum PlayerShipFactory
{
	INSTANCE;

	public IShip create(GameplayStageLegacy stage)
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
		//@formatter:on

		IPool energyPool = new Pool(50, 10, 10, 2);
		IPool hpPool = new HpPool(50, 10, 5, 1);
		Bar energyBar = new HpEnergyBar(energyPool, hpPool);
		energyBar.initGdx();

		stage.setPrimaryWeapon(redLaser);
		stage.setSecondaryWeapon(redLaser);
		stage.addActor(energyBar);

		ship.loadComplexGraphics(Textures.PLAYER_SHIP_F.getTexture(), Textures.PLAYER_SHIP_R.getTexture(),
				Textures.PLAYER_SHIP_L.getTexture());
		ship.setShipEngine(engine);
		ship.addWeapon(redLaser);
		ship.setEnergyPool(energyPool);
		ship.setHpPool(hpPool);

		return ship;
	}
}
