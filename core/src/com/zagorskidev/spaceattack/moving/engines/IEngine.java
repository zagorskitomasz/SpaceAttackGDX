package com.zagorskidev.spaceattack.moving.engines;

import com.badlogic.gdx.math.Vector2;
import com.zagorskidev.spaceattack.ships.IShip.Turn;

public interface IEngine
{
	public void setDestination(Vector2 destination);

	public Turn fly();

	public void setLevel(int level);
}
