package com.zagorskidev.spaceattack.graphics;

import com.badlogic.gdx.graphics.Texture;

public enum StaticImageFactory
{
	INSTANCE;

	public StaticImage create(Texture texture,float x,float y)
	{
		StaticImage image = instantiateImage();
		image.init(texture, x, y);

		return image;
	}

	public StaticImage instantiateImage()
	{
		return new StaticImage();
	}
}
