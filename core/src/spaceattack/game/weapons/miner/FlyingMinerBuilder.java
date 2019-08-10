package spaceattack.game.weapons.miner;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum FlyingMinerBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        FlyingMiner miner = new FlyingMiner(armory);

        miner.setUtils(Factories.getUtilsFactory().create());
        miner.setController(weaponController);
        miner.setMissilesLauncher(missilesLauncher);

        return miner;
    }

    public IWeapon buildDelayed(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        FlyingMiner miner = new FlyingMiner(armory);

        miner.setUtils(Factories.getUtilsFactory().create());
        miner.setController(weaponController);
        miner.setMissilesLauncher(missilesLauncher);
        miner.increaseDelay();

        return miner;
    }
}
