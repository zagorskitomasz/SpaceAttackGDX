package com.zagorskidev.spaceattack.weapons;

import com.zagorskidev.spaceattack.weapons.missiles.AbstractMissile;

public interface IMissileLauncher
{
	public void launch(AbstractMissile missile);
}
