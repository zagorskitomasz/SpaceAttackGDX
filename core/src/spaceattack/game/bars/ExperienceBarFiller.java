package spaceattack.game.bars;

import spaceattack.consts.Sizes;
import spaceattack.game.bars.AbstractBarFiller;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;

public class ExperienceBarFiller extends AbstractBarFiller
{
	public ExperienceBarFiller(IPool pool)
	{
		super(pool);
	}

	@Override
	public void drawRect(IBatch batch)
	{
		batch.setColor(0.6f, 0.1f, 0.6f, 1);
		batch.rect(Sizes.GAME_WIDTH - 22, (Sizes.GAME_HEIGHT - 500) * 0.5f + 20, 13, percent * 460);
	}
}
