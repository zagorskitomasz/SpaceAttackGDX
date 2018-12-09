package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.system.GameSaver;

public abstract class AbstractStage extends Stage implements IStage
{
	protected Stages type;
	private StageResult result;
	protected GameProgress gameProgress;

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
	public void setResult(StageResult result,boolean forceSave)
	{
		this.result = result;
		if (result.getGameProgress() != null && (!result.getGameProgress().equals(gameProgress) || forceSave))
			saveProgress(GameSaver.INSTANCE);
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
		if (gameProgress == null)
			gameProgress = new GameProgress();

		return gameProgress;
	}

	@Override
	public void setGameProgress(GameProgress gameProgress)
	{
		this.gameProgress = gameProgress;
	}

	@Override
	public void saveProgress(GameSaver saver)
	{
		saver.save(gameProgress);
	}
}
