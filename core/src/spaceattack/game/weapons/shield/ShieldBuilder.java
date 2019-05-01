package spaceattack.game.weapons.shield;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum ShieldBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher) {

        EnergyShieldEmiter shieldEmiter = new EnergyShieldEmiter();

        shieldEmiter.setUtils(Factories.getUtilsFactory().create());
        shieldEmiter.setController(weaponController);
        shieldEmiter.setMissilesLauncher(missilesLauncher);
        shieldEmiter.setLevel(1);

        return shieldEmiter;
    }
}
