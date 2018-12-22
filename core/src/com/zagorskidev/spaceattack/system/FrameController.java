package com.zagorskidev.spaceattack.system;

import com.badlogic.gdx.utils.TimeUtils;

public class FrameController
{
	private int checksPerSecond;
	private long lastFrameTime;

	public FrameController(int checksPerSecond)
	{
		this.checksPerSecond = checksPerSecond;
	}

	public boolean check()
	{
		long currentTime = getCurrentTime();
		if (currentTime >= lastFrameTime + 1000 / checksPerSecond)
		{
			lastFrameTime = currentTime;
			return true;
		}
		return false;
	}

	long getCurrentTime()
	{
		return TimeUtils.millis();
	}
}
