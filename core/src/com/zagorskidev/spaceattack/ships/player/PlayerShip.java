package com.zagorskidev.spaceattack.ships.player;

import com.badlogic.gdx.graphics.Texture;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.stages.GameplayStage;

public class PlayerShip extends Ship
{
	private GameplayStage stage;

	PlayerShip(GameplayStage stage,Texture texture)
	{
		super(texture);

		this.stage = stage;
		setPosition(Consts.GAME_WIDTH * 0.5f, Consts.GAME_HEIGHT * 0.3f);
	}
}
