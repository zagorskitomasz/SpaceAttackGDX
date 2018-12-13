package com.zagorskidev.spaceattack.ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.moving.engines.IEngine;

public interface IShip
{
	public enum Turn
	{
		FRONT,LEFT,RIGHT;
	}

	public Actor getActor();

	public void draw(Batch batch,float alpha);

	public void setDestination(Vector2 destination);

	public float getX();

	public float getY();

	public void setX(float x);

	public void setY(float y);

	public void setShipEngine(IEngine engine);

	public void loadGraphics(String texturePath);
}
