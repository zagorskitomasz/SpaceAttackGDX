package com.zagorskidev.spaceattack.ships;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public abstract class Ship extends Actor implements IShip
{
	protected Texture currentTexture;

	protected IEngine engine;
	protected Set<IWeapon> weapons;

	public Ship()
	{

	}

	@Override
	public void loadGraphics(String texturePath)
	{
		currentTexture = createTexture(texturePath);
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
			fly();
	}

	protected void fly()
	{
		engine.fly();
	}

	@Override
	public void addWeapon(IWeapon weapon)
	{
		if (weapons == null)
			weapons = new CopyOnWriteArraySet<>();
	}

	@Override
	public Set<IWeapon> getWeapons()
	{
		return weapons;
	}

	@Override
	public void setLevel(int level)
	{
		engine.setLevel(level);

		for (IWeapon weapon : weapons)
			weapon.setLevel(level);
	}
}
