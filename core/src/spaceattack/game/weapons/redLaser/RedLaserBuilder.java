package spaceattack.game.weapons.redLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.laser.Laser;

public enum RedLaserBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        Laser redLaser = new RedLaser(armory);

        redLaser.setUtils(Factories.getUtilsFactory().create());
        redLaser.setController(weaponController);
        redLaser.setMissilesLauncher(missilesLauncher);

        return redLaser;
    }
}
