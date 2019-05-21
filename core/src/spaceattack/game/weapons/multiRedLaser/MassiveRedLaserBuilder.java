package spaceattack.game.weapons.multiRedLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.laser.Laser;

public enum MassiveRedLaserBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher) {

        Laser redLaser = new MassiveRedLaser();

        redLaser.setUtils(Factories.getUtilsFactory().create());
        redLaser.setController(weaponController);
        redLaser.setMissilesLauncher(missilesLauncher);
        redLaser.setLevel(1);

        return redLaser;
    }
}
