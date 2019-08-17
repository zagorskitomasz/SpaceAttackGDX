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

    public IWeapon createRedLaser(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory, final int mastery) {

        return RedLaserBuilder.INSTANCE.build(weaponController, missilesLauncher, armory, mastery);
    }

    public IWeapon createGreenLaser(final PlayerWeaponController weaponController,
            final MissilesLauncher missilesLauncher,
            final int armory, final int mastery) {

        return GreenLaserBuilder.INSTANCE.build(weaponController, missilesLauncher, armory, mastery);
    }

    public IWeapon createRocketMissile(final IWeaponController weaponController,
            final MissilesLauncher missilesLauncher,
            final int armory) {

        return RocketMissileBuilder.INSTANCE.build(weaponController, missilesLauncher, armory);
    }

    public IWeapon createDoubleRedLaser(final PlayerWeaponController weaponController,
            final MissilesLauncher missilesLauncher,
            final int armory, final int mastery) {

        return DoubleRedLaserBuilder.INSTANCE.build(weaponController, missilesLauncher, armory, mastery);
    }

    public IWeapon createTripleGreenLaser(final PlayerWeaponController weaponController,
            final MissilesLauncher missilesLauncher,
            final int armory, final int mastery) {

        return TripleGreenLaserBuilder.INSTANCE.build(weaponController, missilesLauncher, armory, mastery);
    }

    public IWeapon createMine(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        return MinerBuilder.INSTANCE.build(weaponController, missilesLauncher, armory);
    }

    public IWeapon createShield(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        return ShieldBuilder.INSTANCE.build(weaponController, missilesLauncher, armory);
    }

    public IWeapon createFlamethrower(final IWeaponController weaponController,
            final MissilesLauncher missilesLauncher,
            final int armory) {

        return FlamethrowerBuilder.INSTANCE.build(weaponController, missilesLauncher, armory);
    }

    public IWeapon createTimeWave(final IWeaponController weaponController, final MissilesLauncher missilesLauncher,
            final int armory) {

        return TimeWaveEmitterBuilder.INSTANCE.build(weaponController, missilesLauncher, armory);
    }

    public IWeapon createMassiveRedLaser(final PlayerWeaponController weaponController,
            final MissilesLauncher missilesLauncher,
            final int armory, final int mastery) {

        return MassiveRedLaserBuilder.INSTANCE.build(weaponController, missilesLauncher, armory, mastery);
    }
}
