package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.ships.IPool;

public class HpBarFiller extends AbstractBarFiller
{
	public HpBarFiller(IPool hpPool)
	{
		super(hpPool);
	}

	@Override
	public void initLabel()
	{
		super.initLabel();

		label.setAlignment(Align.topLeft);
		label.setPosition(30, Sizes.gameHeight() - 10);
	}

	@Override
	public void drawRect(ShapeRenderer renderer)
	{
		renderer.setColor(0.6f, 0.1f, 0.1f, 1);
		renderer.rect(20, Sizes.gameHeight() - 31, (179 * percent), 22);
	}
}
