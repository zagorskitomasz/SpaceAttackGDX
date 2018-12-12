package com.zagorskidev.spaceattack.ships.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
		PlayerShip ship = new PlayerShip(new Texture(Gdx.files.internal(Paths.PLAYER_SHIP)));

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

		ship.setShipEngine(engine);

		return ship;
	}
}
