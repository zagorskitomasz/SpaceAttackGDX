package spaceattack.game.bars;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.utils.IUtils;

public class EnergyBarFiller extends AbstractBarFiller
{
	public EnergyBarFiller(IPool energyPool,IUtils utils)
	{
		super(energyPool, utils);

		label.setAlignment(Consts.Align.topRight);
		label.setPosition(Sizes.GAME_WIDTH - 30, Sizes.GAME_WIDTH - 10);
	}

	@Override
	public void drawRect(IBatch batch)
	{
		batch.setColor(0.1f, 0.1f, 0.6f, 1);
		batch.rect(Sizes.GAME_WIDTH - 20, Sizes.GAME_HEIGHT - 31, -(179 * percent), 22);
	}
}
