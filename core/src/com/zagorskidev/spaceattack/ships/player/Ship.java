package com.zagorskidev.spaceattack.ships.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.ships.IShip;

public abstract class Ship extends Actor implements IShip
{
	protected Texture texture;
	private Vector2 destination;

	public Ship(Texture texture)
	{
		this.texture = texture;
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
		this.destination = destination;
	}

	@Override
	public void act(float delta)
	{
		moveToDestination();
	}

	private void moveToDestination()
	{
		if (destination == null)
			return;

		if (destination.x == this.getX() || destination.y == this.getY())
			return;

		setX(destination.x);
		setY(destination.y);
	}
}
