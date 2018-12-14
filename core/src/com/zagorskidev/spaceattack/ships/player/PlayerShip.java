package com.zagorskidev.spaceattack.ships.player;

import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.ships.Ship;

public class PlayerShip extends Ship
{
	PlayerShip()
	{
		super();
		setPosition(Sizes.gameWidth() * 0.5f, Sizes.gameHeight() * 0.3f);
	}
}
