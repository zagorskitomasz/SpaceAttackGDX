package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.notifiers.IObserver;
import com.zagorskidev.spaceattack.ships.IPool;

public class HpEnergyBar extends Bar
{
	private ShapeRenderer renderer;

	private IPool energyPool;

	private float maxEnergy;
	private float energy;
	private float energyPercent;

	private Texture texture;
	private Label energyLabel;

	public HpEnergyBar(IPool energyPool)
	{
		this.energyPool = energyPool;

		IObserver<Float> energyObserver = new ValueObserver(this::energyChanged);
		energyPool.registerObserver(energyObserver);

		texture = Textures.HP_ENE_BAR.getTexture();
	}

	@Override
	public void initGdx()
	{
		renderer = buildRenderer();
		energyLabel = buildLabel();
	}

	ShapeRenderer buildRenderer()
	{
		return new ShapeRenderer();
	}

	Label buildLabel()
	{
		BitmapFont font = new BitmapFont(Gdx.files.internal(Paths.GAMEPLAY_FONT));
		Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);

		Label label = new Label("", style);
		label.setAlignment(Align.topRight);
		label.setPosition(Sizes.gameWidth() - 30, Sizes.gameHeight() - 10);

		return label;
	}

	public void energyChanged(float amount)
	{
		energy = energyPool.getAmount();
		maxEnergy = energyPool.getMaxAmount();
		energyPercent = amount;

		if (energyLabel != null)
			energyLabel.setText(String.format("%.0f", energy) + " / " + String.format("%.0f", maxEnergy));
	}

	@Override
	public void draw(Batch batch,float aplpha)
	{
		batch.end();

		drawBarRect(batch);

		batch.begin();

		energyLabel.draw(batch, 0.8f);
		if (texture != null)
			batch.draw(texture, 0, Sizes.gameHeight() - texture.getHeight());
	}

	void drawBarRect(Batch batch)
	{
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.begin(ShapeType.Filled);
		renderer.setColor(0.1f, 0.1f, 0.6f, 1);
		renderer.rect(Sizes.gameWidth() - 20, Sizes.gameHeight() - 31, -(179 * energyPercent), 22);
		renderer.end();
	}

	float getMaxEnergy()
	{
		return maxEnergy;
	}

	float getEnergy()
	{
		return energy;
	}

	float getEnergyPercent()
	{
		return energyPercent;
	}
}
