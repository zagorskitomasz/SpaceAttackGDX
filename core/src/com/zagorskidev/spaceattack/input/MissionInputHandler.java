package com.zagorskidev.spaceattack.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.ships.IShip;

public class MissionInputHandler extends InputAdapter implements IInput
{
	private IShip ship;

	@Override
	public void registerShip(IShip ship)
	{
		this.ship = ship;
	}

	@Override
	public boolean touchUp(int screenX,int screenY,int pointer,int button)
	{
		if (ship != null)
			ship.setDestination(new Vector2(screenX, Sizes.gameHeight() - screenY));

		return true;
	}
}
