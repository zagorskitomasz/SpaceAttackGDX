package spaceattack.game.weapons.laser;

import spaceattack.consts.Consts;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Missile;

public abstract class Laser implements IWeapon
{
	@SuppressWarnings("unused")
	private IUtils utils;
	private FrameController frameController;
	protected IWeaponController controller;
	private MissilesLauncher launcher;

	protected float dmg;
	protected float speed;
	protected float energyCost;

	protected abstract Missile buildMissile();

	public void setUtils(IUtils utils)
	{
		this.utils = utils;
		frameController = new FrameController(utils, Consts.Weapons.LASER_ATTACKS_PER_SECOND);
	}

	public void setController(IWeaponController weaponController)
	{
		this.controller = weaponController;
	}

	public void setMissilesLauncher(MissilesLauncher missilesLauncher)
	{
		launcher = missilesLauncher;
	}

	@Override
	public boolean use()
	{
		if (!frameController.check())
			return false;

		if (!controller.takeEnergy(energyCost))
			return false;

		Missile missile = buildMissile();
		launcher.launch(missile);
		return true;
	}

	@Override
	public float getEnergyCost()
	{
		return energyCost;
	}

	@Override
	public float getWeaponsMovementFactor()
	{
		return 0.7f;
	}

	@Override
	public float getCollisionRadius()
	{
		return Consts.Weapons.LASER_RADIUS;
	}
}
