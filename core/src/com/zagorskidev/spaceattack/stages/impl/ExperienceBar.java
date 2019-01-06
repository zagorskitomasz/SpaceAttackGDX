package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.ships.IPool;

import spaceattack.consts.Sizes;

public class ExperienceBar extends Bar
{
	private IPool expPool;
	private AbstractBarFiller expFiller;

	public ExperienceBar(IPool pool)
	{
		expPool = pool;
		expFiller = new ExperienceBarFiller(pool);
		texture = Textures.EXP_BAR.getTexture();
	}

	@Override
	protected void drawFillerLabel(Batch batch)
	{
		// do nothing
	}

	@Override
	protected void drawTexture(Batch batch)
	{
		batch.draw(texture, Sizes.gameWidth() - texture.getWidth(), (Sizes.gameHeight() - texture.getHeight()) * 0.5f);
	}

	@Override
	protected void drawFillerRect()
	{
		expFiller.drawRect(renderer);
	}

	@Override
	public void act(float delta)
	{
		expPool.update();
	}
}
