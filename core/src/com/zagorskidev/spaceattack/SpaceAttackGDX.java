package com.zagorskidev.spaceattack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageFactory;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.system.GameLoader;

public class SpaceAttackGDX extends ApplicationAdapter
{
	private StageFactory factory;
	private IStage stage;

	@Override
	public void create()
	{
		factory = getStageFactory();
		StageResult defaultResult = new StageResult();
		stage = factory.getStage(defaultResult);
		stage.loadGame(GameLoader.INSTANCE);
		setStageAsInputProcessor();
	}

	void setStageAsInputProcessor()
	{
		Gdx.input.setInputProcessor((Stage) stage);
	}

	StageFactory getStageFactory()
	{
		return StageFactory.INSTANCE;
	}

	@Override
	public void render()
	{
		clearScreen();

		stage.act(getDeltaTime());
		stage.draw();

		checkStageSwitch();
	}

	private void checkStageSwitch()
	{
		if (stage.isCompleted())
		{
			StageResult result = stage.getResult();
			stage = factory.getStage(result);
			setStageAsInputProcessor();
		}
	}

	void clearScreen()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	}

	float getDeltaTime()
	{
		return Gdx.graphics.getDeltaTime();
	}

	Stages getCurrentStage()
	{
		return stage.getType();
	}

	@Override
	public void resize(int width,int height)
	{
		if (stage != null)
			stage.getViewport().update(width, height, true);
	}
}
