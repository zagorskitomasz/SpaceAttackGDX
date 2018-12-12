package com.zagorskidev.spaceattack.ships.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.ships.IShip;

public abstract class Ship extends Actor implements IShip
{
	private Texture texture;
	private IEngine engine;

	public Ship(Texture texture)
	{
		this.texture = texture;
	}

	@Override
	public void setShipEngine(IEngine engine)
	{
		this.engine = engine;
	}

	@Override
	public Actor getActor()
	{
		return this;
	}

	@Override
	public void draw(Batch batch,float alpha)
	{
		batch.draw(texture, getDrawingX(), getDrawingY());
	}

	private float getDrawingX()
	{
		return getX() - texture.getWidth() * 0.5f;
	}

	private float getDrawingY()
	{
		return getY() - texture.getHeight() * 0.5f;
	}

	@Override
	public void setDestination(Vector2 destination)
	{
		if (engine != null)
			engine.setDestination(destination);
	}

	@Override
	public void act(float delta)
	{
		if (engine != null)
			engine.fly();
	}
}
