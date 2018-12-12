package com.zagorskidev.spaceattack.moving.engines;

import com.badlogic.gdx.math.Vector2;

public interface IEngine
{
	public void setDestination(Vector2 destination);

	public void fly();
}
