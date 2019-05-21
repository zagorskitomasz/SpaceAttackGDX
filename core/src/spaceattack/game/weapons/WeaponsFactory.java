package spaceattack.game.weapons;

import spaceattack.game.weapons.flame.FlamethrowerBuilder;
import spaceattack.game.weapons.greenLaser.GreenLaserBuilder;
import spaceattack.game.weapons.miner.MinerBuilder;
import spaceattack.game.weapons.multiRedLaser.DoubleRedLaserBuilder;
import spaceattack.game.weapons.multiRedLaser.MassiveRedLaserBuilder;
import spaceattack.game.weapons.redLaser.RedLaserBuilder;
import spaceattack.game.weapons.rocketMissile.RocketMissileBuilder;
import spaceattack.game.weapons.shield.ShieldBuilder;
import spaceattack.game.weapons.timeWave.TimeWaveEmitterBuilder;
import spaceattack.game.weapons.tripleGreenLaser.TripleGreenLaserBuilder;

public enum WeaponsFactory {
    INSTANCE;

    public IWeapon createRedLaser(final IWeaponController weaponController, final MissilesLauncher missilesLauncher) {

        return RedLaserBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }

    public IWeapon createGreenLaser(final PlayerWeaponController weaponController,
            final MissilesLauncher missilesLauncher) {

        return GreenLaserBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }

    public IWeapon createRocketMissile(final IWeaponController weaponController,
            final MissilesLauncher missilesLauncher) {

        return RocketMissileBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }

    public IWeapon createDoubleRedLaser(final PlayerWeaponController weaponController,
            final MissilesLauncher missilesLauncher) {

        return DoubleRedLaserBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }

    public IWeapon createTripleGreenLaser(final PlayerWeaponController weaponController,
            final MissilesLauncher missilesLauncher) {

        return TripleGreenLaserBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }

    public IWeapon createMine(final IWeaponController weaponController, final MissilesLauncher missilesLauncher) {

        return MinerBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }

    public IWeapon createShield(final IWeaponController weaponController, final MissilesLauncher missilesLauncher) {

        return ShieldBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }

    public IWeapon createFlamethrower(final IWeaponController weaponController,
            final MissilesLauncher missilesLauncher) {

        return FlamethrowerBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }

    public IWeapon createTimeWave(final IWeaponController weaponController, final MissilesLauncher missilesLauncher) {

        return TimeWaveEmitterBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }

    public IWeapon createMassiveRedLaser(final PlayerWeaponController weaponController,
            final MissilesLauncher missilesLauncher) {

        return MassiveRedLaserBuilder.INSTANCE.build(weaponController, missilesLauncher);
    }
}
