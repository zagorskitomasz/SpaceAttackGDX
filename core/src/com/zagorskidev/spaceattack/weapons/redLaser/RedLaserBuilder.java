package com.zagorskidev.spaceattack.weapons.redLaser;

import com.zagorskidev.spaceattack.weapons.IMissileLauncher;
import com.zagorskidev.spaceattack.weapons.IWeaponController;
import com.zagorskidev.spaceattack.weapons.builders.ControllerSet;
import com.zagorskidev.spaceattack.weapons.builders.LauncherSet;
import com.zagorskidev.spaceattack.weapons.builders.LevelSet;

public class RedLaserBuilder implements IRedLaserBuilder,ControllerSet,LauncherSet,LevelSet
{
	private RedLaser weapon;

	public RedLaserBuilder()
	{
		weapon = new RedLaser();
	}

	@Override
	public ControllerSet setController(IWeaponController controller)
	{
		weapon.setController(controller);
		return this;
	}

	@Override
	public LauncherSet setMissileLauncher(IMissileLauncher launcher)
	{
		weapon.setLauncher(launcher);
		return this;
	}

	@Override
	public LevelSet setLevel(int level)
	{
		weapon.setLevel(level);
		return this;
	}

	@Override
	public RedLaser build()
	{
		return weapon;
	}
}
