package com.zagorskidev.spaceattack.graphics;

import com.badlogic.gdx.Gdx;

public class Sizes
{
	public static final float BASIC_WIDTH = 400;
	public static final float BASIC_HEIGHT = 750;

	public static float gameWidth()
	{
		return Gdx.graphics != null ? Gdx.graphics.getWidth() : 400;
	}

	public static float gameHeight()
	{
		return Gdx.graphics != null ? Gdx.graphics.getHeight() : 750;
	}

	public static float buttonWidth()
	{
		return gameWidth() * 0.6f;
	}

	public static float buttonHeight()
	{
		return gameHeight() * 0.09f;
	}
}
