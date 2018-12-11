package com.zagorskidev.spaceattack.ships.player;

import com.badlogic.gdx.graphics.Texture;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.stages.GameplayStage;

public class PlayerShip extends Ship
{
	private GameplayStage stage;

	PlayerShip(GameplayStage stage,Texture texture)
	{
		super(texture);

		this.stage = stage;
		setPosition(Sizes.gameWidth() * 0.5f, Sizes.gameHeight() * 0.3f);
	}
}
