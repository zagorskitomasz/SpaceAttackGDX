package spaceattack.game.weapons.greenLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.laser.Laser;

public enum GreenLaserBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        return build(weaponController, missilesLauncher, armory, 0);
    }

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory, final int mastery) {

        Laser greenLaser = new GreenLaser(armory, mastery);

        greenLaser.setUtils(Factories.getUtilsFactory().create());
        greenLaser.setController(weaponController);
        greenLaser.setMissilesLauncher(missilesLauncher);

        return greenLaser;
    }
}
