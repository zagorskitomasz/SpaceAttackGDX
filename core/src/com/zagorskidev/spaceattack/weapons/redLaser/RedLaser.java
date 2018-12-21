package com.zagorskidev.spaceattack.weapons.redLaser;

import com.zagorskidev.spaceattack.weapons.IMissileLauncher;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.IWeaponController;

public class RedLaser implements IWeapon
{
	private IWeaponController controller;
	private IMissileLauncher launcher;
	private int level;

	RedLaser()
	{

	}

	@Override
	public void setLevel(int level)
	{
		this.level = level;
	}

	@Override
	public void use()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public float getWeaponsMovementFactor()
	{
		return 1;
	}

	IWeaponController getController()
	{
		return controller;
	}

	void setController(IWeaponController controller)
	{
		this.controller = controller;
	}

	IMissileLauncher getLauncher()
	{
		return launcher;
	}

	void setLauncher(IMissileLauncher launcher)
	{
		this.launcher = launcher;
	}
}
