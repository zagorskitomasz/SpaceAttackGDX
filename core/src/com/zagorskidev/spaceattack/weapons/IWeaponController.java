package com.zagorskidev.spaceattack.weapons;

import com.badlogic.gdx.math.Vector2;

public interface IWeaponController
{
	public void setPrimaryWeapon(IWeapon weapon);

	public void setSecondaryWeapon(IWeapon weapon);

	public Vector2 getPrimaryWeaponUsePlacement();

	public Vector2 getSecondaryWeaponUsePlacement();

	public Vector2 getWeaponMovement();

	public boolean takeEnergy(float energyCost);

	public void updateSecondaryWeapon(IWeapon weapon);
}
