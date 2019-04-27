package spaceattack.game.weapons.rocketMissile;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum RocketMissileBuilder {
    INSTANCE;

    public IWeapon build(IWeaponController weaponController, MissilesLauncher missilesLauncher) {

        RocketMissile rocketMissile = new RocketMissile();

        rocketMissile.setUtils(Factories.getUtilsFactory().create());
        rocketMissile.setController(weaponController);
        rocketMissile.setMissilesLauncher(missilesLauncher);
        rocketMissile.setLevel(1);

        return rocketMissile;
    }
}
