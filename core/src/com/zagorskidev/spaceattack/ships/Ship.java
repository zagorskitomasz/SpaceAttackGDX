package com.zagorskidev.spaceattack.ships;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.weapons.IWeapon;

import spaceattack.game.actors.DrawableActor;

public abstract class Ship extends DrawableActor implements IShip
{
	protected Texture currentTexture;

	protected IEngine engine;
	protected Set<IWeapon> weapons;

	private IPool energyPool;
	private IPool hpPool;

	private boolean isToKill;

	public Ship()
	{

	}

	@Override
	public void loadGraphics(Texture texture)
	{
		currentTexture = texture;
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
	public void setEnergyPool(IPool pool)
	{
		energyPool = pool;
	}

	public void setHpPool(IPool pool)
	{
		hpPool = pool;
	}

	@Override
	public void act(float delta)
	{
		if (energyPool != null)
			energyPool.update();

		if (hpPool != null)
			hpPool.update();

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

		weapons.add(weapon);
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

		if (energyPool != null)
			energyPool.setLevel(level);

		if (hpPool != null)
			hpPool.setLevel(level);

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

	@Override
	public void takeDmg(float dmg)
	{
		if (!hpPool.take(dmg))
		{
			setToKill();
			energyPool.destroy();
		}
	}

	@Override
	public float getRadius()
	{
		return (getHeight() + getWidth()) * 0.25f;
	}

	@Override
	public Vector2 getPosition()
	{
		return new Vector2(getX(), getY());
	}

	@Override
	public void setToKill()
	{
		isToKill = true;
	}

	@Override
	public boolean isToKill()
	{
		return isToKill;
	}

	@Override
	public boolean takeEnergy(float energyCost)
	{
		return energyPool.take(energyCost);
	}

	@Override
	public IPool getEnergyPool()
	{
		return energyPool;
	}
}
