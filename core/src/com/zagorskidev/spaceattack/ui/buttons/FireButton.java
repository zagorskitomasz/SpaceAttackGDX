package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.zagorskidev.spaceattack.ui.ActorGUI;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public abstract class FireButton extends Button implements ActorGUI
{
	protected IWeapon weapon;
	private Vector2 buttonCenter;

	public FireButton(Drawable drawableUp,Drawable drawableDown)
	{
		super(drawableUp, drawableDown);
		setPosition();
		buttonCenter = new Vector2(getX() + 0.5f * getWidth(), getY() + 0.5f * getHeight());
	}

	protected abstract void setPosition();

	public void setDisabledTexture(Drawable drawable)
	{
		this.getStyle().disabled = drawable;
	}

	public void setWeapon(IWeapon weapon)
	{
		this.weapon = weapon;
	}

	private double distance(Vector2 object1,Vector2 object2)
	{
		return Math.sqrt(Math.pow((object2.x - object1.x), 2) + Math.pow((object2.y - object1.y), 2));
	}

	public boolean touchDown(int screenX,int screenY)
	{
		return touch(screenX, screenY, InputEvent.Type.touchDown);
	}

	public boolean touchUp(int screenX,int screenY)
	{
		if (touch(screenX, screenY, InputEvent.Type.touchUp))
		{
			if (weapon != null)
				weapon.use();
			return true;
		}
		return false;
	}

	private boolean touch(int screenX,int screenY,InputEvent.Type type)
	{
		if (touched(screenX, screenY))
		{
			InputEvent event = createEvent(type);
			fire(event);
			return true;
		}
		if ((InputEvent.Type.touchUp.equals(type) && isPressed()))
		{
			InputEvent event = createEvent(type);
			fire(event);
		}
		return false;
	}

	private boolean touched(int screenX,int screenY)
	{
		Vector2 touch = new Vector2(screenX, screenY);
		touch = getStage().screenToStageCoordinates(touch);

		return distance(touch, buttonCenter) <= getWidth() * 0.5;
	}

	private InputEvent createEvent(Type type)
	{
		InputEvent event = new InputEvent();
		event.setType(type);
		return event;
	}

	void setButtonCenter(Vector2 center)
	{
		buttonCenter = center;
	}
}
