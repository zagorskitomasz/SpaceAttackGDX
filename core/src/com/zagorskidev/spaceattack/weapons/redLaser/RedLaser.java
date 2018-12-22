package com.zagorskidev.spaceattack.weapons.redLaser;

import com.badlogic.gdx.graphics.Texture;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.graphics.TexturesFactory;
import com.zagorskidev.spaceattack.system.FrameController;
import com.zagorskidev.spaceattack.weapons.IMissileLauncher;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.IWeaponController;
import com.zagorskidev.spaceattack.weapons.missiles.Missile;
import com.zagorskidev.spaceattack.weapons.missiles.MissilesBuilder;

public class RedLaser implements IWeapon
{
	private FrameController frameController;
	private IWeaponController controller;
	private IMissileLauncher launcher;

	private float dmg;
	private float speed;

	RedLaser()
	{
		frameController = new FrameController(Consts.LASER_INTERVAL);
	}

	@Override
	public void setLevel(int level)
	{
		dmg = Consts.RED_LASER_BASE_DMG + (level - 1) * Consts.RED_LASER_DMG_PER_LEVEL;
		speed = Consts.RED_LASER_BASE_SPEED + (level - 1) * Consts.RED_LASER_SPEED_PER_LEVEL;
	}

	@Override
	public void use()
	{
		if (!frameController.check())
			return;

		Missile missile = buildMissile();
		launcher.launch(missile);
	}

	Missile buildMissile()
	{
		//@formatter:off
		return MissilesBuilder
				.INSTANCE
				.init()
				.setTexture(getMissileTexture())
				.setDmg(dmg)
				.setSpeed(speed)
				.setAcceleration(0)
				.setMovement(controller.getPrimaryWeaponMovement())
				.setPosition(controller.getPrimaryWeaponUsePlacement())
				.build();
		//@formatter:on
	}

	@Override
	public float getWeaponsMovementFactor()
	{
		return 0.7f;
	}

	Texture getMissileTexture()
	{
		return TexturesFactory.INSTANCE.createTexture(Paths.RED_LASER);
	}

	IWeaponController getController()
	{
		return controller;
	}

	void setController(IWeaponController controller)
	{
		this.controller = controller;
	}

	IMissileLauncher getLauncher()
	{
		return launcher;
	}

	void setLauncher(IMissileLauncher launcher)
	{
		this.launcher = launcher;
	}
}
