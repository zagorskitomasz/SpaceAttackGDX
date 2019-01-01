package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.notifiers.IObserver;
import com.zagorskidev.spaceattack.ships.IPool;

public abstract class AbstractBarFiller
{
	private IPool pool;

	private float amount;
	private float maxAmount;
	protected float percent;

	protected Label label;

	public AbstractBarFiller(IPool pool)
	{
		this.pool = pool;

		IObserver<Float> observer = new ValueObserver(this::valueChanged);
		pool.registerObserver(observer);
	}

	public void valueChanged(float percent)
	{
		amount = pool.getAmount();
		maxAmount = pool.getMaxAmount();
		this.percent = percent;

		if (label != null)
			label.setText(String.format("%.0f", amount) + " / " + String.format("%.0f", maxAmount));
	}

	public void initLabel()
	{
		BitmapFont font = new BitmapFont(Gdx.files.internal(Paths.GAMEPLAY_FONT));
		Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
		label = new Label("", style);
	}

	public abstract void drawRect(ShapeRenderer renderer);

	public void drawLabel(Batch batch)
	{
		if (label != null)
			label.draw(batch, 0.8f);
	}

	public float getMaxAmount()
	{
		return maxAmount;
	}

	public float getAmount()
	{
		return amount;
	}

	public float getPercent()
	{
		return percent;
	}
}
