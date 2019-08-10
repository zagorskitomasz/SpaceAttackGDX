package spaceattack.game.weapons.flame;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum FlamethrowerBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        Flamethrower flamethrower = new Flamethrower(armory);

        flamethrower.setUtils(Factories.getUtilsFactory().create());
        flamethrower.setController(weaponController);
        flamethrower.setMissilesLauncher(missilesLauncher);

        return flamethrower;
    }
}
