package com.zagorskidev.spaceattack.ships;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.moving.engines.IEngine;

public abstract class Ship extends Actor implements IShip
{
	private Texture currentTexture;

	private Map<IShip.Turn, Texture> textures;

	private IEngine engine;

	public Ship()
	{

	}

	@Override
	public void loadGraphics(String texturePath)
	{
		textures = new HashMap<>();
		textures.put(IShip.Turn.FRONT, createTexture(texturePath));
		textures.put(IShip.Turn.LEFT, createTexture(texturePath.replaceAll(".png", "L.png")));
		textures.put(IShip.Turn.RIGHT, createTexture(texturePath.replaceAll(".png", "R.png")));

		currentTexture = textures.get(IShip.Turn.FRONT);
	}

	public Texture createTexture(String texturePath)
	{
		return new Texture(Gdx.files.internal(texturePath));
	}

	@Override
	public void setShipEngine(IEngine engine)
	{
		this.engine = engine;
	}

	@Override
	public Actor getActor()
	{
		return this;
	}

	@Override
	public void draw(Batch batch,float alpha)
	{
		if (currentTexture != null)
			batch.draw(currentTexture, getDrawingX(), getDrawingY());
	}

	private float getDrawingX()
	{
		return getX() - currentTexture.getWidth() * 0.5f;
	}

	private float getDrawingY()
	{
		return getY() - currentTexture.getHeight() * 0.5f;
	}

	@Override
	public void setDestination(Vector2 destination)
	{
		if (engine != null)
			engine.setDestination(destination);
	}

	@Override
	public void act(float delta)
	{
		if (engine != null)
		{
			IShip.Turn turn = engine.fly();
			currentTexture = textures.get(turn);
		}
	}
}
