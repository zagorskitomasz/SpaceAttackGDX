package spaceattack.ext.utils;

import java.io.InputStream;
import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;

import spaceattack.consts.Paths;
import spaceattack.ext.stage.GdxStage;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.stages.IStage;
import spaceattack.game.system.IFileHandle;

enum GdxUtils implements IGdxUtils
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

	@Override
	public Skin getUiSkin()
	{
		return new Skin(Gdx.files.internal(Paths.UI_STYLE));
	}

	@Override
	public void confirmDialog(String caption,String question,IStage stage,Consumer<Boolean> resultProcessor)
	{
		GdxDialog dialog = new GdxDialog(caption);
		dialog.text(question);
		dialog.button("Yes", true);
		dialog.button("No", false);
		dialog.key(Keys.ENTER, true);
		dialog.setResultProcessor(resultProcessor);

		dialog.show((GdxStage) stage);
	}

	@Override
	public void exit()
	{
		Gdx.app.exit();
	}

	@Override
	public float atan2(float x,float y)
	{
		return MathUtils.atan2(y, x);
	}

	@Override
	public float radiansToDegrees()
	{
		return MathUtils.radiansToDegrees;
	}
}