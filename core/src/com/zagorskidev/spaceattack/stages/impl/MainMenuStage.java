package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.ui.buttons.ContinueGameButton;
import com.zagorskidev.spaceattack.ui.buttons.ExitGameButton;
import com.zagorskidev.spaceattack.ui.buttons.NewGameButton;

public class MainMenuStage extends UIStage
{
	private Stages type;
	private Stages result;

	public MainMenuStage()
	{
		init();
	}

	void init()
	{
		addActor(createNewGameButton());
		addActor(createContinueGameButton());
		addActor(createExitGameButton());
	}

	@Override
	public boolean isCompleted()
	{
		return result != null;
	}

	@Override
	public Stages getResult()
	{
		return result;
	}

	@Override
	public void setResult(Stages result)
	{
		this.result = result;
	}

	@Override
	public Stages getType()
	{
		return type;
	}

	@Override
	public void setType(Stages type)
	{
		this.type = type;
	}

	Button createNewGameButton()
	{
		return new NewGameButton(this);
	}

	Button createContinueGameButton()
	{
		return new ContinueGameButton(this);
	}

	Button createExitGameButton()
	{
		return new ExitGameButton(this);
	}
}
