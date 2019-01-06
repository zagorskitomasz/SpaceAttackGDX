package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zagorskidev.spaceattack.ships.IPool;

import spaceattack.consts.Sizes;

public class ExperienceBarFiller extends AbstractBarFiller
{

	public ExperienceBarFiller(IPool pool)
	{
		super(pool);
	}

	@Override
	public void drawRect(ShapeRenderer renderer)
	{
		renderer.setColor(0.6f, 0.1f, 0.6f, 1);
		renderer.rect(Sizes.gameWidth() - 22, (Sizes.gameHeight() - 500) * 0.5f + 20, 13, percent * 460);
	}
}
