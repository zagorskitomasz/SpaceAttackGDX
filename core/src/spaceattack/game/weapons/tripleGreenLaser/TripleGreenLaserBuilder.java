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

        Laser greenLaser = new TripleGreenLaser(armory);

        greenLaser.setUtils(Factories.getUtilsFactory().create());
        greenLaser.setController(weaponController);
        greenLaser.setMissilesLauncher(missilesLauncher);

        return greenLaser;
    }
}
