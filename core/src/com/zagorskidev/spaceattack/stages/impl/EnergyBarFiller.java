package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.ships.IPool;

public class EnergyBarFiller extends AbstractBarFiller
{
	public EnergyBarFiller(IPool energyPool)
	{
		super(energyPool);
	}

	@Override
	public void initLabel()
	{
		super.initLabel();

		label.setAlignment(Align.topRight);
		label.setPosition(Sizes.gameWidth() - 30, Sizes.gameHeight() - 10);
	}

	@Override
	public void drawRect(ShapeRenderer renderer)
	{
		renderer.setColor(0.1f, 0.1f, 0.6f, 1);
		renderer.rect(Sizes.gameWidth() - 20, Sizes.gameHeight() - 31, -(179 * percent), 22);
	}
}
