package com.zagorskidev.spaceattack.ships.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.ships.IShip;

public abstract class Ship extends Actor implements IShip
{
	protected Texture texture;

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
}
