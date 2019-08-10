package spaceattack.game.weapons.rocketMissile;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum RocketMissileBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        RocketMissile rocketMissile = new RocketMissile(armory);

        rocketMissile.setUtils(Factories.getUtilsFactory().create());
        rocketMissile.setController(weaponController);
        rocketMissile.setMissilesLauncher(missilesLauncher);

        return rocketMissile;
    }
}
