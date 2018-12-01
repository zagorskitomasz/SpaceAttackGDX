package com.zagorskidev.spaceattack.stages;

import com.zagorskidev.spaceattack.system.GameProgress;

public class StageResult
{
	private Stages nextStage;
	private GameProgress gameProgress;

	public StageResult()
	{
		nextStage = Stages.MAIN_MENU;
	}

	public Stages getNextStage()
	{
		return nextStage;
	}

	public void setNextStage(Stages nextStage)
	{
		this.nextStage = nextStage;
	}

	public GameProgress getGameProgress()
	{
		return gameProgress;
	}

	public void setGameProgress(GameProgress gameProgress)
	{
		this.gameProgress = gameProgress;
	}
}
