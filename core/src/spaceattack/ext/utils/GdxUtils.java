package spaceattack.ext.utils;

import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;

import spaceattack.consts.Paths;
import spaceattack.ext.stage.GdxStage;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.stages.IStage;
import spaceattack.game.system.IFileHandle;
import spaceattack.game.utils.IUtils;

enum GdxUtils implements IUtils
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

	@Override
	public <T> T streamToObject(Class<T> clazz,InputStream fileContent)
	{
		Json json = new Json();
		return json.fromJson(clazz, fileContent);
	}

	@Override
	public IFileHandle loadFile(String save)
	{
		return new GdxFileHandle(Gdx.files.local(Paths.SAVE));
	}

	@Override
	public double pow(double value,double exponent)
	{
		return Math.pow(value, exponent);
	}

	@Override
	public double sqrt(double value)
	{
		return Math.sqrt(value);
	}

	@Override
	public <T> String objectToString(T object,Class<T> clazz)
	{
		Json json = new Json();
		return json.toJson(object, clazz);
	}

	@Override
	public void setInputProcessor(IStage stage)
	{
		Gdx.input.setInputProcessor((GdxStage) stage);
	}

	@Override
	public void setInputProcessor(IInputProcessor inputProcessor)
	{
		Gdx.input.setInputProcessor(new GdxInput(inputProcessor));
	}
}
