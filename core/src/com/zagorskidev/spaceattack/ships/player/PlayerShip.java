package com.zagorskidev.spaceattack.ships.player;

import com.badlogic.gdx.graphics.Texture;
import com.zagorskidev.spaceattack.graphics.Sizes;

public class PlayerShip extends Ship
{
	PlayerShip(Texture texture)
	{
		super(texture);

		setPosition(Sizes.gameWidth() * 0.5f, Sizes.gameHeight() * 0.3f);
	}
}
