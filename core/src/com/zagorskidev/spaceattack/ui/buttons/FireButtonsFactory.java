package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public enum FireButtonsFactory
{
	INSTANCE;

	public FireButton primary(IStage stage,IWeapon weapon)
	{
		Drawable drawableUp = getDrawable(Paths.Graphics.UI.PF_BUTTON_UP);
		Drawable drawableDown = getDrawable(Paths.Graphics.UI.PF_BUTTON_DOWN);

		FireButton button = instantiatePrimary(drawableUp, drawableDown);
		button.setWeapon(weapon);

		stage.addActor(button);

		return button;
	}

	PrimaryFireButton instantiatePrimary(Drawable drawableUp,Drawable drawableDown)
	{
		return new PrimaryFireButton(drawableUp, drawableDown);
	}

	Drawable getDrawable(String path)
	{
		Texture texture = new Texture(path);
		TextureRegion region = new TextureRegion(texture);
		Drawable drawable = new TextureRegionDrawable(region);

		return drawable;
	}
}
