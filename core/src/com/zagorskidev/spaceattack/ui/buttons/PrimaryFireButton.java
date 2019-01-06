package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import spaceattack.consts.Sizes;

public class PrimaryFireButton extends FireButton
{
	PrimaryFireButton(Drawable drawableUp,Drawable drawableDown)
	{
		super(drawableUp, drawableDown);
	}

	@Override
	protected void setPosition()
	{
		setPosition(Sizes.gameWidth() * 0.5f - getWidth() * 0.5f, Sizes.gameHeight() * 0.08f);
	}
}
