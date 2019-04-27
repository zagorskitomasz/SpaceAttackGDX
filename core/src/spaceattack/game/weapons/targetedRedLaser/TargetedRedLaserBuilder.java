package spaceattack.game.weapons.targetedRedLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum TargetedRedLaserBuilder {
    INSTANCE;

    public IWeapon build(IWeaponController weaponController, MissilesLauncher missilesLauncher) {

        TargetedRedLaser laser = new TargetedRedLaser();

        laser.setUtils(Factories.getUtilsFactory().create());
        laser.setController(weaponController);
        laser.setMissilesLauncher(missilesLauncher);
        laser.setLevel(1);

        return laser;
    }
}
