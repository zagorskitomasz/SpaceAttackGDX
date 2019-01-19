package spaceattack.game.weapons.redLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.laser.Laser;

public enum RedLaserBuilder
{
	INSTANCE;

	public IWeapon build(IWeaponController weaponController,MissilesLauncher missilesLauncher)
	{
		Laser redLaser = new RedLaser();

		redLaser.setUtils(Factories.getUtilsFactory().create());
		redLaser.setController(weaponController);
		redLaser.setMissilesLauncher(missilesLauncher);
		redLaser.setLevel(1);

		return redLaser;
	}
}
