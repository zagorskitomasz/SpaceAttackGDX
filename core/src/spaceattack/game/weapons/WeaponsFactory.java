package spaceattack.game.weapons;

import spaceattack.game.weapons.greenLaser.GreenLaserBuilder;
import spaceattack.game.weapons.miner.MinerBuilder;
import spaceattack.game.weapons.multiRedLaser.DoubleRedLaserBuilder;
import spaceattack.game.weapons.redLaser.RedLaserBuilder;
import spaceattack.game.weapons.rocketMissile.RocketMissileBuilder;

public enum WeaponsFactory
{
	INSTANCE;

	public IWeapon createRedLaser(IWeaponController weaponController,MissilesLauncher missilesLauncher)
	{
		return RedLaserBuilder.INSTANCE.build(weaponController, missilesLauncher);
	}

	public IWeapon createGreenLaser(PlayerWeaponController weaponController,MissilesLauncher missilesLauncher)
	{
		return GreenLaserBuilder.INSTANCE.build(weaponController, missilesLauncher);
	}

	public IWeapon createRocketMissile(IWeaponController weaponController,MissilesLauncher missilesLauncher)
	{
		return RocketMissileBuilder.INSTANCE.build(weaponController, missilesLauncher);
	}

	public IWeapon createDoubleRedLaser(PlayerWeaponController weaponController, MissilesLauncher missilesLauncher) 
	{
		return DoubleRedLaserBuilder.INSTANCE.build(weaponController, missilesLauncher);
	}

	public IWeapon createMine(IWeaponController weaponController, MissilesLauncher missilesLauncher) 
	{
		return MinerBuilder.INSTANCE.build(weaponController, missilesLauncher);
	}
}
