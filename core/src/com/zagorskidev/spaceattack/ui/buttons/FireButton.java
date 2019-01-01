package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.notifiers.IObserver;
import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.system.NumbersUtils;
import com.zagorskidev.spaceattack.ui.ActorGUI;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public abstract class FireButton extends Button implements ActorGUI,IObserver<Float>
{
	protected IWeapon weapon;
	private IPool energyPool;

	private Vector2 buttonCenter;

	public FireButton(Drawable drawableUp,Drawable drawableDown)
	{
		super(drawableUp, drawableDown);
		setDisabledTexture(Textures.FIRE_BUTTON_DISABLED.getTexture());
		setPosition();
		buttonCenter = new Vector2(getX() + 0.5f * getWidth(), getY() + 0.5f * getHeight());
	}

	protected abstract void setPosition();

	private void setDisabledTexture(Texture texture)
	{
		getStyle().disabled = new TextureRegionDrawable(new TextureRegion(texture));
	}

	public void setWeapon(IWeapon weapon)
	{
		this.weapon = weapon;
	}

	private double distance(Vector2 object1,Vector2 object2)
	{
		return NumbersUtils.distance(object1, object2);
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

	@Override
	public void notify(Float state)
	{
		if (!isDisabled() && energyPool.getAmount() < weapon.getEnergyCost())
		{
			setDisabled(true);
			setTouchable(Touchable.disabled);
		}
		else if (isDisabled() && energyPool.getAmount() >= weapon.getEnergyCost())
		{
			setDisabled(false);
			setTouchable(Touchable.enabled);
		}
	}

	public void setEnergyPool(IPool pool)
	{
		this.energyPool = pool;
		pool.registerObserver(this);
	}
}
