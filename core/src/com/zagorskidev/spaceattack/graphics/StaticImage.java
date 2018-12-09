package com.zagorskidev.spaceattack.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.consts.Consts;

public class StaticImage extends Actor
{
	private float x;
	private float y;
	private Texture texture;

	void init(String path,float x,float y)
	{
		texture = createTexture(path);
		this.x = x;
		this.y = gameHeight() - (y + texture.getHeight());
	}

	float gameHeight()
	{
		return Consts.GAME_HEIGHT;
	}

	Texture createTexture(String path)
	{
		return new Texture(Gdx.files.internal(path));
	}

	@Override
	public void draw(Batch batch,float alpha)
	{
		batch.draw(texture, x, y);
	}
}
