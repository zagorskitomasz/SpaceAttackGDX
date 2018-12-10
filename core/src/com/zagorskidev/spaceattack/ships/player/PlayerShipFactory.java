package com.zagorskidev.spaceattack.ships.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.stages.GameplayStage;

public enum PlayerShipFactory
{
	INSTANCE;

	public IShip create(GameplayStage stage)
	{
		PlayerShip ship = new PlayerShip(stage, new Texture(Gdx.files.internal(Paths.PLAYER_SHIP)));
		return ship;
	}
}
