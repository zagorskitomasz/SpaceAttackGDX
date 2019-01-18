package spaceattack.game.system;

import spaceattack.game.utils.IUtils;

public class FrameController
{
	private IUtils utils;

	private int checksPerSecond;
	private long lastFrameTime;

	public FrameController(IUtils utils,int checksPerSecond)
	{
		this.utils = utils;
		this.checksPerSecond = checksPerSecond;
	}

	public boolean check()
	{
		long currentTime = utils.getCurrentTime();
		if (currentTime >= lastFrameTime + 1000 / checksPerSecond)
		{
			lastFrameTime = currentTime;
			return true;
		}
		return false;
	}
}
