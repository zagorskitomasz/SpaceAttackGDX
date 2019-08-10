package spaceattack.game.weapons.rocketMissile;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum DoubleRocketBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        DoubleRocketMissile doubleRocketMissile = new DoubleRocketMissile(armory);

        doubleRocketMissile.setUtils(Factories.getUtilsFactory().create());
        doubleRocketMissile.setController(weaponController);
        doubleRocketMissile.setMissilesLauncher(missilesLauncher);

        return doubleRocketMissile;
    }
}
