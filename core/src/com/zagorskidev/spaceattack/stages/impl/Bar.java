package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Bar extends Actor
{
	protected ShapeRenderer renderer;
	protected Texture texture;

	public void initGdx()
	{
		renderer = buildRenderer();
	}

	ShapeRenderer buildRenderer()
	{
		return new ShapeRenderer();
	}

	protected void drawBarRect(Batch batch)
	{
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.begin(ShapeType.Filled);

		drawFillerRect();

		renderer.end();
	}

	@Override
	public void draw(Batch batch,float aplpha)
	{
		batch.end();

		drawBarRect(batch);

		batch.begin();

		drawFillerLabel(batch);

		if (texture != null)
			drawTexture(batch);
	}

	protected abstract void drawFillerLabel(Batch batch);

	protected abstract void drawTexture(Batch batch);

	protected abstract void drawFillerRect();
}
