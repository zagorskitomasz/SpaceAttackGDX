package spaceattack.game.weapons.flame;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum FlamethrowerBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher) {

        Flamethrower flamethrower = new Flamethrower();

        flamethrower.setUtils(Factories.getUtilsFactory().create());
        flamethrower.setController(weaponController);
        flamethrower.setMissilesLauncher(missilesLauncher);
        flamethrower.setLevel(1);

        return flamethrower;
    }
}
