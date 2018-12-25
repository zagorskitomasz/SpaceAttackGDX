package com.zagorskidev.spaceattack.ships;

import java.util.Set;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.moving.engines.IEngine;
import com.zagorskidev.spaceattack.notifiers.Observer;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.missiles.Vulnerable;

public interface IShip extends Observer<GameProgress>,Vulnerable
{
	public enum Turn
	{
		FRONT,LEFT,RIGHT;
	}

	public Actor getActor();

	public void draw(Batch batch,float alpha);

	public void setDestination(Vector2 destination);

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
}
