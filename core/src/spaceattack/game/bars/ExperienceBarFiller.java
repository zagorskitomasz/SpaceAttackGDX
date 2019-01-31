package spaceattack.game.bars;

import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.utils.IUtils;

public class ExperienceBarFiller extends AbstractBarFiller
{
	public ExperienceBarFiller(IPool pool,IUtils utils)
	{
		super(pool, utils);
	}

	@Override
	public void drawRect(IBatch batch)
	{
		batch.setColor(0.6f, 0.1f, 0.6f, 1);
		batch.rect( //
				Sizes.GAME_WIDTH - 9 - Sizes.EXP_BAR_WIDTH, //
				Sizes.GAME_HEIGHT - 900 + 22, //
				Sizes.EXP_BAR_WIDTH, //
				(800 - 44) * percent);
	}
}
