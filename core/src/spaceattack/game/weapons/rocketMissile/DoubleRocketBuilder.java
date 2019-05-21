package spaceattack.game.weapons.rocketMissile;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum DoubleRocketBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher) {

        DoubleRocketMissile doubleRocketMissile = new DoubleRocketMissile();

        doubleRocketMissile.setUtils(Factories.getUtilsFactory().create());
        doubleRocketMissile.setController(weaponController);
        doubleRocketMissile.setMissilesLauncher(missilesLauncher);
        doubleRocketMissile.setLevel(1);

        return doubleRocketMissile;
    }
}
