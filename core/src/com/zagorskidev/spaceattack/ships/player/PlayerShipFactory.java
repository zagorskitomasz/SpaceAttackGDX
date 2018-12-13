package com.zagorskidev.spaceattack.ships.player;

import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.moving.engines.ShipEngineBuilder;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.stages.GameplayStage;

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

		ship.loadGraphics(Paths.PLAYER_SHIP);
		ship.setShipEngine(engine);

		return ship;
	}
}
