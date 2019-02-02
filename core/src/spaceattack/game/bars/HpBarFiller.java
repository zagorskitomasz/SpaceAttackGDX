package spaceattack.game.bars;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.utils.IUtils;

public class HpBarFiller extends AbstractBarFiller
{
	public HpBarFiller(IPool hpPool,IUtils utils)
	{
		super(hpPool, utils);

		label.setAlignment(Consts.Align.topLeft);
		label.setPosition(30, Sizes.GAME_HEIGHT - 10);
	}

	@Override
	public void drawRect(IBatch batch)
	{
		batch.setColor(0.6f, 0.1f, 0.1f, 1);
		batch.rect( //
				22, //
				Sizes.GAME_HEIGHT - 9 - Sizes.HP_ENE_BAR_WIDTH, //
				(Sizes.GAME_WIDTH * 0.5f - 22) * percent, //
				Sizes.HP_ENE_BAR_WIDTH);
	}
}
