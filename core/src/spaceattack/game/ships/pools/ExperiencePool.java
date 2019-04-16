package spaceattack.game.ships.pools;

import spaceattack.consts.Experience;
import spaceattack.game.GameProgress;

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
		long nextLevelReq = Experience.INSTANCE.expForLevel(gameProgress.getLevel() + 1);
		long thisLevelReq = Experience.INSTANCE.expForLevel(gameProgress.getLevel());

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
