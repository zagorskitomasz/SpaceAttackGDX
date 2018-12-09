package com.zagorskidev.spaceattack.graphics;

public enum StaticImageFactory
{
	INSTANCE;

	public StaticImage create(String path,float x,float y)
	{
		StaticImage image = instantiateImage();
		image.init(path, x, y);

		return image;
	}

	public StaticImage instantiateImage()
	{
		return new StaticImage();
	}
}
