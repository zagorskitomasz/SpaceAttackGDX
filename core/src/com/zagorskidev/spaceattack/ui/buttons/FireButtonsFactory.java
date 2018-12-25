package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public enum FireButtonsFactory
{
	INSTANCE;

	public FireButton primary(IStage stage,IWeapon weapon)
	{
		Drawable drawableUp = getDrawable(Textures.PF_BUTTON_UP.getTexture());
		Drawable drawableDown = getDrawable(Textures.PF_BUTTON_DOWN.getTexture());

		FireButton button = instantiatePrimary(drawableUp, drawableDown);
		button.setWeapon(weapon);

		stage.addActor(button);

		return button;
	}

	PrimaryFireButton instantiatePrimary(Drawable drawableUp,Drawable drawableDown)
	{
		return new PrimaryFireButton(drawableUp, drawableDown);
	}

	Drawable getDrawable(Texture texture)
	{
		TextureRegion region = new TextureRegion(texture);
		Drawable drawable = new TextureRegionDrawable(region);

		return drawable;
	}
}
