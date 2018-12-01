package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameLoader;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.ContinueGameButton;
import com.zagorskidev.spaceattack.ui.buttons.ExitGameButton;
import com.zagorskidev.spaceattack.ui.buttons.NewGameButton;

public class MainMenuStage extends UIStage
{
	private Stages type;
	private StageResult result;
	private GameProgress gameProgress;

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
	public StageResult getResult()
	{
		return result;
	}

	@Override
	public void setResult(StageResult result)
	{
		this.result = result;
		if (result.getGameProgress() == null)
			result.setGameProgress(gameProgress);
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

	@Override
	public GameProgress getGameProgress()
	{
		return gameProgress;
	}

	@Override
	public void setGameProgress(GameProgress gameProgress)
	{
		this.gameProgress = gameProgress;
	}

	@Override
	public void loadGame(GameLoader loader)
	{
		gameProgress = loader.load(this);
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
