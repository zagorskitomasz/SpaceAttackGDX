package spaceattack.game.weapons.miner;

import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;

public enum MinerBuilder
{
	INSTANCE;

	public IWeapon build(IWeaponController weaponController,MissilesLauncher missilesLauncher)
	{
		Miner miner = new Miner();

		miner.setUtils(Factories.getUtilsFactory().create());
		miner.setController(weaponController);
		miner.setMissilesLauncher(missilesLauncher);
		miner.setLevel(1);

		return miner;
	}
}
