package com.zagorskidev.spaceattack.weapons.missiles;

import com.badlogic.gdx.math.Vector2;

public interface Vulnerable extends Killable
{
	public void takeDmg(float dmg);

	public float getRadius();

	public Vector2 getPosition();
}
