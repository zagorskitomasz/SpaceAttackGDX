package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public class FireButton extends Button
{
	protected IWeapon weapon;
	private boolean clicked = false;

	public FireButton(Drawable drawableUp,Drawable drawableDown)
	{
		super(drawableUp, drawableDown);
		setPosition(100, 100);

		addListener(new ClickListener()
		{
			@Override
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button)
			{
				clicked = true;
				return true;
			}
		});
	}

	public void setDisabledTexture(Drawable drawable)
	{
		this.getStyle().disabled = drawable;
	}

	public void setWeapon(IWeapon weapon)
	{
		this.weapon = weapon;
	}

	public boolean touch(int screenX,int screenY)
	{
		if (!isDisabled() && clicked)
		{
			clicked = false;
			weapon.use();
			return true;
		}
		return false;
	}
}
