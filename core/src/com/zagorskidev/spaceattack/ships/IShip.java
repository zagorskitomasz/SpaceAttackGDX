package com.zagorskidev.spaceattack.ships;

import java.util.Set;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.missiles.Vulnerable;

import spaceattack.game.GameProgress;
import spaceattack.game.system.notifiers.IObserver;
import spaceattack.game.utils.vector.IVector;

public interface IShip extends IObserver<GameProgress>,Vulnerable
{
	public enum Turn
	{
		FRONT,LEFT,RIGHT;
	}

	public Actor getActor();

	public void draw(Batch batch,float alpha);

	public void setDestination(IVector iVector);

	public float getX();

	public float getY();

	public void setX(float x);

	public void setY(float y);

	public void setShipEngine(IEngine engine);

	public void loadGraphics(Texture texture);

	public void addWeapon(IWeapon weapon);

	public Set<IWeapon> getWeapons();

	void setLevel(int level);

	public float getHeight();

	public float getWidth();

	public boolean takeEnergy(float energyCost);

	public void setEnergyPool(IPool pool);

	public IPool getEnergyPool();
}
