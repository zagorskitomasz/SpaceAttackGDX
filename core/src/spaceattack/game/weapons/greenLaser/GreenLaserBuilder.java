package spaceattack.game.weapons.greenLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.laser.Laser;

public enum GreenLaserBuilder
{
	INSTANCE;

	public IWeapon build(IWeaponController weaponController,MissilesLauncher missilesLauncher)
	{
		Laser greenLaser = new GreenLaser();

		greenLaser.setUtils(Factories.getUtilsFactory().create());
		greenLaser.setController(weaponController);
		greenLaser.setMissilesLauncher(missilesLauncher);
		greenLaser.setLevel(1);

		return greenLaser;
	}
}
