package spaceattack.game.weapons.redLaser;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Missile;

public class RedLaser implements IWeapon
{
	@SuppressWarnings("unused")
	private IUtils utils;
	private FrameController frameController;
	private IWeaponController controller;
	private MissilesLauncher launcher;

	private float dmg;
	private float speed;
	private float energyCost;

	RedLaser()
	{
		// do nothing
	}

	void setUtils(IUtils utils)
	{
		this.utils = utils;
		frameController = new FrameController(utils, Consts.Weapons.LASER_ATTACKS_PER_SECOND);
	}

	void setController(IWeaponController weaponController)
	{
		this.controller = weaponController;
	}

	void setMissilesLauncher(MissilesLauncher missilesLauncher)
	{
		launcher = missilesLauncher;
	}

	@Override
	public void setLevel(int level)
	{
		dmg = Consts.Weapons.RED_LASER_BASE_DMG + (level - 1) * Consts.Weapons.RED_LASER_DMG_PER_LEVEL;
		speed = Consts.Weapons.RED_LASER_BASE_SPEED + (level - 1) * Consts.Weapons.RED_LASER_SPEED_PER_LEVEL;
		energyCost = Consts.Weapons.RED_LASER_BASE_COST + (level - 1) * Consts.Weapons.RED_LASER_COST_PER_LEVEL;
	}

	@Override
	public void use()
	{
		if (!controller.takeEnergy(energyCost))
			return;

		if (!frameController.check())
			return;

		Missile missile = buildMissile();
		launcher.launch(missile);
	}

	Missile buildMissile()
	{
		Missile missile = new Missile();

		missile.setActor(Factories.getActorFactory().create(missile));
		missile.setTexture(Textures.RED_LASER.getTexture());
		missile.setDmg(dmg);
		missile.setSpeed(speed);
		missile.setAcceleration(0);
		missile.setMovement(controller.getWeaponMovement());
		missile.setPosition(controller.getPrimaryWeaponUsePlacement());
		missile.setSound(Sounds.RED_LASER);

		return missile;
	}

	@Override
	public float getWeaponsMovementFactor()
	{
		return 0.7f;
	}

	@Override
	public float getEnergyCost()
	{
		return energyCost;
	}
}
