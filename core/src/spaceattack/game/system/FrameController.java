package spaceattack.game.system;

import spaceattack.game.utils.IUtils;

public class FrameController
{
	private IUtils utils;

	private float checksPerSecond;
	private long lastFrameTime;

	public FrameController(IUtils utils,float checksPerSecond)
	{
		this.utils = utils;
		this.checksPerSecond = checksPerSecond;
	}

	public boolean check()
	{
		long currentTime = utils.getCurrentTime();
		if (currentTime >= lastFrameTime + (int) (1000 / checksPerSecond))
		{
			lastFrameTime = currentTime;
			return true;
		}
		return false;
	}
}
