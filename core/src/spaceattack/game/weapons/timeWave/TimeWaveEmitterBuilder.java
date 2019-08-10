package spaceattack.game.weapons.timeWave;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum TimeWaveEmitterBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        TimeWaveEmitter waveEmitter = new TimeWaveEmitter(armory);

        waveEmitter.setUtils(Factories.getUtilsFactory().create());
        waveEmitter.setController(weaponController);
        waveEmitter.setMissilesLauncher(missilesLauncher);

        return waveEmitter;
    }
}
