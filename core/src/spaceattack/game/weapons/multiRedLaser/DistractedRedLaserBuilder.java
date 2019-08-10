package spaceattack.game.weapons.multiRedLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum DistractedRedLaserBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        DistractedRedLaser laser = new DistractedRedLaser(armory);

        laser.setUtils(Factories.getUtilsFactory().create());
        laser.setController(weaponController);
        laser.setMissilesLauncher(missilesLauncher);

        return laser;
    }
}
