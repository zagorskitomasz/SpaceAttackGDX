package com.zagorskidev.spaceattack.ships.player;

import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.moving.engines.ShipEngineBuilder;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.stages.GameplayStage;
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
		//@formatter:on

		IWeapon redLaser = WeaponFactory.INSTANCE.redLaser();
		stage.setPrimaryWeapon(redLaser);

		ship.loadGraphics(Paths.PLAYER_SHIP);
		ship.setShipEngine(engine);
		ship.addWeapon(redLaser);

		return ship;
	}
}
