package com.zagorskidev.spaceattack.ships;

import com.zagorskidev.spaceattack.consts.Experience;
import com.zagorskidev.spaceattack.system.GameProgress;

public class ExperiencePool extends AbstractPool
{
	private GameProgress gameProgress;
	private GameProgress backup;

	public ExperiencePool(GameProgress gameProgress,GameProgress backup)
	{
		super();
		this.gameProgress = gameProgress;
		this.backup = backup;
	}

	@Override
	public void update()
	{
		long nextLevelReq = Experience.nextLevelReq[gameProgress.getLevel()];
		long thisLevelReq = Experience.nextLevelReq[gameProgress.getLevel() - 1];

		maxAmount = nextLevelReq - thisLevelReq;
		amount = gameProgress.getExperience() - thisLevelReq;

		notifyObservers();
	}

	@Override
	public void destroy()
	{
		gameProgress = backup;
		update();
	}

	@Override
	public boolean take(float amount)
	{
		// do nothing
		return true;
	}

	@Override
	public void setLevel(int level)
	{
		// do nothing
	}
}
