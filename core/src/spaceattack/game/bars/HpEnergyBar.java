package spaceattack.game.bars;

import spaceattack.consts.Sizes;
import spaceattack.game.bars.AbstractBarFiller;
import spaceattack.game.bars.Bar;
import spaceattack.game.bars.EnergyBarFiller;
import spaceattack.game.bars.HpBarFiller;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.Textures;

public class HpEnergyBar extends Bar
{
	private AbstractBarFiller energyFiller;
	private AbstractBarFiller hpFiller;

	public HpEnergyBar(IPool energyPool,IPool hpPool)
	{
		energyFiller = new EnergyBarFiller(energyPool);
		hpFiller = new HpBarFiller(hpPool);

		texture = Textures.HP_ENE_BAR.getTexture();
	}

	@Override
	protected void drawTexture(IBatch batch)
	{
		batch.draw(texture, 0, Sizes.GAME_HEIGHT - texture.getHeight());
	}

	@Override
	protected void drawFillerLabel(IBatch batch)
	{
		energyFiller.drawLabel(batch);
		hpFiller.drawLabel(batch);
	}

	@Override
	protected void drawBarRect(IBatch batch)
	{
		energyFiller.drawRect(batch);
		hpFiller.drawRect(batch);
	}

	float getMaxEnergy()
	{
		return energyFiller.getMaxAmount();
	}

	float getEnergy()
	{
		return energyFiller.getAmount();
	}

	float getEnergyPercent()
	{
		return energyFiller.getPercent();
	}

	float getMaxHp()
	{
		return hpFiller.getMaxAmount();
	}

	float getHp()
	{
		return hpFiller.getAmount();
	}

	float getHpPercent()
	{
		return hpFiller.getPercent();
	}
}
