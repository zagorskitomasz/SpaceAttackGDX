package com.zagorskidev.spaceattack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;

public class SpaceAttackGDX extends ApplicationAdapter
{
	@Override
	public void create()
	{
		// TODO
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void dispose()
	{
		// TODO
	}
}
