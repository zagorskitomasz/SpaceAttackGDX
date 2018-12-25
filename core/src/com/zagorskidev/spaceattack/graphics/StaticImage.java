package com.zagorskidev.spaceattack.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class StaticImage extends Actor
{
	private float x;
	private float y;
	private Texture texture;

	void init(Texture texture,float x,float y)
	{
		this.texture = texture;
		this.x = x;
		this.y = gameHeight() - (y + texture.getHeight());
	}

	float gameHeight()
	{
		return Sizes.gameHeight();
	}

	@Override
	public void draw(Batch batch,float alpha)
	{
		batch.draw(texture, x, y);
	}
}
