package com.zagorskidev.spaceattack.weapons;

import com.zagorskidev.spaceattack.weapons.redLaser.IRedLaserBuilder;
import com.zagorskidev.spaceattack.weapons.redLaser.RedLaserBuilder;

public enum WeaponFactory
{
	INSTANCE;

	public IRedLaserBuilder redLaser()
	{
		return new RedLaserBuilder();
	}
}
