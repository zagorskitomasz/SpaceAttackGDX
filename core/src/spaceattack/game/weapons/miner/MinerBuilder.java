package spaceattack.game.weapons.miner;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum MinerBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        Miner miner = new Miner(armory);

        miner.setUtils(Factories.getUtilsFactory().create());
        miner.setController(weaponController);
        miner.setMissilesLauncher(missilesLauncher);

        return miner;
    }
}
