package spaceattack.ext.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.utils.TimeUtils;

import spaceattack.game.utils.ExtUtils;

enum GdxUtils implements ExtUtils
{
	INSTANCE;

	@Override
	public void clearScreen()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public float getDeltaTime()
	{
		return Gdx.graphics.getDeltaTime();
	}

	@Override
	public long getCurrentTime()
	{
		return TimeUtils.millis();
	}
}
