package spaceattack.game.weapons.tripleGreenLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.laser.Laser;

public enum TripleGreenLaserBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        return build(weaponController, missilesLauncher, armory, 0, 0);
    }

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory, final int mastery, final int speedFactor) {

        Laser greenLaser = new TripleGreenLaser(armory, mastery, speedFactor);

        greenLaser.setUtils(Factories.getUtilsFactory().create());
        greenLaser.setController(weaponController);
        greenLaser.setMissilesLauncher(missilesLauncher);

        return greenLaser;
    }
}
