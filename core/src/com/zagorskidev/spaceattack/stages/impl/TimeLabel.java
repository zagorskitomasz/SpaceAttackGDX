package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.graphics.Sizes;

public class TimeLabel extends Actor
{
	private String text;
	private Label label;
	private long showed;

	public TimeLabel(String text)
	{
		showed = 0;
		this.text = text;
	}

	public void initGdx()
	{
		label = createLabel();
	}

	Label createLabel()
	{
		BitmapFont font = new BitmapFont(Gdx.files.internal(Paths.TIME_LABELS_FONT));
		Label.LabelStyle style = new Label.LabelStyle(font, Color.GOLDENROD);
		Label label = new Label(text, style);
		label.setPosition((Sizes.gameWidth() - label.getWidth()) * 0.5f, Sizes.gameHeight() * 0.8f);

		return label;
	}

	@Override
	public void draw(Batch batch,float alpha)
	{
		if (isVisible())
			drawLabel(batch, alpha);
	}

	void drawLabel(Batch batch,float alpha)
	{
		label.draw(batch, alpha);
	}

	public void show()
	{
		showed = TimeUtils.millis();
	}

	@Override
	public boolean isVisible()
	{
		return showed + Consts.Gameplay.LABEL_SHOW_TIME > TimeUtils.millis();
	}
}
