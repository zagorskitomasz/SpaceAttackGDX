package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.ships.IPool;

import spaceattack.consts.Sizes;

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
	public void initGdx()
	{
		super.initGdx();

		energyFiller.initLabel();
		hpFiller.initLabel();
	}

	@Override
	protected void drawTexture(Batch batch)
	{
		batch.draw(texture, 0, Sizes.gameHeight() - texture.getHeight());
	}

	@Override
	protected void drawFillerLabel(Batch batch)
	{
		energyFiller.drawLabel(batch);
		hpFiller.drawLabel(batch);
	}

	@Override
	protected void drawFillerRect()
	{
		energyFiller.drawRect(renderer);
		hpFiller.drawRect(renderer);
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
