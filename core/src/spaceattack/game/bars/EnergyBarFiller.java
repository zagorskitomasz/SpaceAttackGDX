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
		label.setPosition(Sizes.GAME_WIDTH - 30, Sizes.GAME_HEIGHT - 10);

		rect.setRed(0.1f);
		rect.setGreen(0.1f);
		rect.setBlue(0.6f);

		rect.setX(Sizes.GAME_WIDTH - 22);
		rect.setY(Sizes.GAME_HEIGHT - 9 - Sizes.HP_ENE_BAR_WIDTH);
		rect.setHeight(Sizes.HP_ENE_BAR_WIDTH);
	}

	@Override
	public void drawRect(IBatch batch)
	{
		rect.setWidth(-((Sizes.GAME_WIDTH * 0.5f - 22) * percent));
		batch.rect(rect);
	}
}
