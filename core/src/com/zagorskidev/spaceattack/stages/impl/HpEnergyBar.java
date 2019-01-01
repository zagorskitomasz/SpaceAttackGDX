package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.ships.IPool;

public class HpEnergyBar extends Bar
{
	private ShapeRenderer renderer;

	private Texture texture;

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
		renderer = buildRenderer();
		energyFiller.initLabel();
		hpFiller.initLabel();
	}

	ShapeRenderer buildRenderer()
	{
		return new ShapeRenderer();
	}

	@Override
	public void draw(Batch batch,float aplpha)
	{
		batch.end();

		drawBarRect(batch);

		batch.begin();

		energyFiller.drawLabel(batch);
		hpFiller.drawLabel(batch);

		if (texture != null)
			batch.draw(texture, 0, Sizes.gameHeight() - texture.getHeight());
	}

	void drawBarRect(Batch batch)
	{
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.begin(ShapeType.Filled);

		energyFiller.drawRect(renderer);
		hpFiller.drawRect(renderer);

		renderer.end();
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
