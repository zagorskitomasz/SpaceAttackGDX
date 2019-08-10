package spaceattack.game.weapons.targetedRedLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum TargetedRedLaserBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        TargetedRedLaser laser = new TargetedRedLaser(armory);

        laser.setUtils(Factories.getUtilsFactory().create());
        laser.setController(weaponController);
        laser.setMissilesLauncher(missilesLauncher);

        return laser;
    }
}
