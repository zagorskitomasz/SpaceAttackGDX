package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.IWeaponController;

import spaceattack.consts.Sizes;

@SuppressWarnings("unused")
public class SecondaryFireButton extends FireButton
{
	private Drawable drawableUp;
	private Drawable drawableDown;
	private Drawable specialDrawableUp;
	private Drawable specialDrawableDown;

	private IWeapon mainWeapon;

	private IWeaponController controller;

	public SecondaryFireButton(Drawable drawableUp,Drawable drawableDown)
	{
		super(drawableUp, drawableDown);
		this.drawableUp = drawableUp;
		this.drawableDown = drawableDown;
	}

	@Override
	protected void setPosition()
	{
		setPosition(Sizes.gameWidth() * 0.25f - getWidth() * 0.5f, Sizes.gameHeight() * 0.20f);
	}

	public void setSpecialTextures(Drawable specialDrawableUp,Drawable specialDrawableDown)
	{
		this.specialDrawableUp = specialDrawableUp;
		this.specialDrawableDown = specialDrawableDown;
	}

	public void setMainWeapon(IWeapon weapon)
	{
		mainWeapon = weapon;
		setWeapon(weapon);
	}

	public void setWeaponController(IWeaponController controller)
	{
		this.controller = controller;
	}

	public IWeapon getMainWeapon()
	{
		return mainWeapon;
	}
}
