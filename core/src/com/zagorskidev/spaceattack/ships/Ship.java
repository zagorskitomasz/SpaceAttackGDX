package com.zagorskidev.spaceattack.ships;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.graphics.DrawableActor;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public abstract class Ship extends DrawableActor implements IShip
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
	protected Texture getTexture()
	{
		return currentTexture;
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
		if (engine != null)
			engine.setLevel(level);

		if (weapons != null)
			for (IWeapon weapon : weapons)
				weapon.setLevel(level);
	}

	@Override
	public float getHeight()
	{
		return currentTexture.getHeight();
	}

	@Override
	public float getWidth()
	{
		return currentTexture.getWidth();
	}
}