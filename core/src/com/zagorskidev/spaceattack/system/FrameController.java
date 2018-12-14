package com.zagorskidev.spaceattack.system;

import com.badlogic.gdx.utils.TimeUtils;
import com.zagorskidev.spaceattack.consts.Consts;

public class FrameController
{
	private long lastFrameTime;

	public boolean check()
	{
		long currentTime = getCurrentTime();
		if (currentTime >= lastFrameTime + 1000 / Consts.FPS)
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
