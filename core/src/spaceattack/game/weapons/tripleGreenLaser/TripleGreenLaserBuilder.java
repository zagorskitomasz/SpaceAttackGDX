package spaceattack.game.weapons.tripleGreenLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.laser.Laser;

public enum TripleGreenLaserBuilder
{
	INSTANCE;

	public IWeapon build(IWeaponController weaponController,MissilesLauncher missilesLauncher)
	{
		Laser greenLaser = new TripleGreenLaser();

		greenLaser.setUtils(Factories.getUtilsFactory().create());
		greenLaser.setController(weaponController);
		greenLaser.setMissilesLauncher(missilesLauncher);
		greenLaser.setLevel(1);

		return greenLaser;
	}
}
