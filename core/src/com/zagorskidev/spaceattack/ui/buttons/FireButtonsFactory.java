package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
		Drawable drawableUp = getDrawable(Paths.PF_BUTTON_UP);
		Drawable drawableDown = getDrawable(Paths.PF_BUTTON_DOWN);

		FireButton button = new PrimaryFireButton(drawableUp, drawableDown);
		button.setWeapon(weapon);

		stage.addActor(button);

		button.addListener(new ClickListener()
		{
			@Override
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button)
			{
				return true;
			}
		});

		return button;
	}

	private Drawable getDrawable(String path)
	{
		Texture texture = new Texture(path);
		TextureRegion region = new TextureRegion(texture);
		Drawable drawable = new TextureRegionDrawable(region);

		return drawable;
	}
}
