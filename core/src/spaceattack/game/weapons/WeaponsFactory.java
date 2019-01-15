package spaceattack.game.weapons;

import spaceattack.game.weapons.redLaser.RedLaserBuilder;

public enum WeaponsFactory
{
	INSTANCE;

	public IWeapon createRedLaser(IWeaponController weaponController,MissilesLauncher missilesLauncher)
	{
		return RedLaserBuilder.INSTANCE.build(weaponController, missilesLauncher);
	}
}
