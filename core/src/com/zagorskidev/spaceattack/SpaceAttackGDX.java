package com.zagorskidev.spaceattack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageFactory;
import com.zagorskidev.spaceattack.stages.Stages;

public class SpaceAttackGDX extends ApplicationAdapter
{
	private StageFactory factory;
	private IStage stage;

	@Override
	public void create()
	{
		factory = getStageFactory();
		stage = factory.getStage(Stages.MAIN_MENU);
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
			Stages result = stage.getResult();
			stage = factory.getStage(result);
		}
	}

	void clearScreen()
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
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
}
