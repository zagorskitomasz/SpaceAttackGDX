package com.zagorskidev.spaceattack.weapons.redLaser;

import com.zagorskidev.spaceattack.weapons.IWeaponController;
import com.zagorskidev.spaceattack.weapons.builders.ControllerSet;

public interface IRedLaserBuilder
{
	public ControllerSet setController(IWeaponController controller);
}
