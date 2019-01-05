package spaceattack.game.system;

import spaceattack.game.utils.ExtUtils;

public class FrameController
{
	private ExtUtils extUtils;

	private int checksPerSecond;
	private long lastFrameTime;

	public FrameController(ExtUtils extUtils,int checksPerSecond)
	{
		this.extUtils = extUtils;
		this.checksPerSecond = checksPerSecond;
	}

	public boolean check()
	{
		long currentTime = extUtils.getCurrentTime();
		if (currentTime >= lastFrameTime + 1000 / checksPerSecond)
		{
			lastFrameTime = currentTime;
			return true;
		}
		return false;
	}
}
