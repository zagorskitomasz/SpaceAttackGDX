package com.zagorskidev.spaceattack.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum TexturesFactory
{
	INSTANCE;

	public Texture createTexture(String texturePath)
	{
		return new Texture(Gdx.files.internal(texturePath));
	}
}
