package spaceattack.game.bars;

import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.Textures;

public class ExperienceBar extends Bar
{
	private IPool expPool;
	private AbstractBarFiller expFiller;

	public ExperienceBar(IPool pool)
	{
		expPool = pool;
		texture = Textures.EXP_BAR.getTexture();
	}

	void setFiller(AbstractBarFiller filler)
	{
		expFiller = filler;
	}

	@Override
	protected void drawFillerLabel(IBatch batch)
	{
		// do nothing
	}

	@Override
	protected void drawTexture(IBatch batch)
	{
		batch.draw(texture, Sizes.GAME_WIDTH - texture.getWidth(), (Sizes.GAME_HEIGHT - texture.getHeight()) * 0.5f);
	}

	@Override
	protected void drawBarRect(IBatch batch)
	{
		expFiller.drawRect(batch);
	}

	@Override
	public void act(float delta)
	{
		expPool.update();
	}
}
