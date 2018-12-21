package com.zagorskidev.spaceattack.weapons;

import com.badlogic.gdx.math.Vector2;

public interface IWeaponController
{
	public void setPrimaryWeapon(IWeapon weapon);

	public Vector2 getPrimaryWeaponUsePlacement();
}
