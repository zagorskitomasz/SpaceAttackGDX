package spaceattack.game.weapons.timeWave;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum TimeWaveEmitterBuilder {
    INSTANCE;

    public IWeapon build(final IWeaponController weaponController, final MissilesLauncher missilesLauncher) {

        TimeWaveEmitter waveEmitter = new TimeWaveEmitter();

        waveEmitter.setUtils(Factories.getUtilsFactory().create());
        waveEmitter.setController(weaponController);
        waveEmitter.setMissilesLauncher(missilesLauncher);
        waveEmitter.setLevel(1);

        return waveEmitter;
    }
}
