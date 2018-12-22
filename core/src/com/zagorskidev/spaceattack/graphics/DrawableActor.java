package com.zagorskidev.spaceattack.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class DrawableActor extends Actor
{
	protected abstract Texture getTexture();

	@Override
	public void draw(Batch batch,float alpha)
	{
		if (getTexture() != null)
			batch.draw(getTexture(), getDrawingX(), getDrawingY());
	}

	private float getDrawingX()
	{
		return getX() - getTexture().getWidth() * 0.5f;
	}

	private float getDrawingY()
	{
		return getY() - getTexture().getHeight() * 0.5f;
	}
}
