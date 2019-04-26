package spaceattack.game.weapons.multiRedLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum DistractedRedLaserBuilder
{
	INSTANCE;

	public IWeapon build(IWeaponController weaponController,MissilesLauncher missilesLauncher)
	{
		DistractedRedLaser laser = new DistractedRedLaser();

		laser.setUtils(Factories.getUtilsFactory().create());
		laser.setController(weaponController);
		laser.setMissilesLauncher(missilesLauncher);
		laser.setLevel(1);

		return laser;
	}
}
