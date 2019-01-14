package spaceattack.game.buttons.weapon;

import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.weapons.IWeapon;

import spaceattack.game.buttons.IImageButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.InputType;
import spaceattack.game.system.notifiers.IObserver;
import spaceattack.game.utils.NumbersUtils;
import spaceattack.game.utils.vector.IVector;

public class FireButton implements IObserver<Float>,IFireButton
{
	private IWeapon weapon;
	private IPool energyPool;
	private IImageButton button;

	private IVector buttonCenter;

	public void setImageButton(IImageButton imageButton)
	{
		button = imageButton;
	}

	@Override
	public void setWeapon(IWeapon weapon)
	{
		this.weapon = weapon;
	}

	public void setPosition(float x,float y)
	{
		button.setX(x);
		button.setY(y);

		buttonCenter = Factories.getVectorFactory().create(x + 0.5f * button.getWidth(), y + 0.5f * button.getHeight());
	}

	private double distance(IVector object1,IVector object2)
	{
		return NumbersUtils.distance(object1, object2);
	}

	@Override
	public boolean touchDown(int screenX,int screenY)
	{
		return touch(screenX, screenY, InputType.TOUCH_DOWN);
	}

	@Override
	public boolean touchUp(int screenX,int screenY)
	{
		if (touch(screenX, screenY, InputType.TOUCH_UP))
		{
			if (weapon != null)
				weapon.use();
			return true;
		}
		return false;
	}

	private boolean touch(int screenX,int screenY,InputType type)
	{
		if (touched(screenX, screenY))
		{
			button.fire(type);
			return true;
		}
		if ((InputType.TOUCH_UP.equals(type) && button.isPressed()))
		{
			button.fire(type);
		}
		return false;
	}

	private boolean touched(int screenX,int screenY)
	{
		IVector touch = Factories.getVectorFactory().create(screenX, screenY);
		touch = button.screenToStageCoordinates(touch);

		return distance(touch, buttonCenter) <= button.getWidth() * 0.5;
	}

	void setButtonCenter(IVector center)
	{
		buttonCenter = center;
	}

	@Override
	public void notify(Float state)
	{
		if (!button.isDisabled() && energyPool.getAmount() < weapon.getEnergyCost())
		{
			button.setEnabled(false);
		}
		else if (button.isDisabled() && energyPool.getAmount() >= weapon.getEnergyCost())
		{
			button.setEnabled(true);
		}
	}

	@Override
	public void setEnergyPool(IPool pool)
	{
		this.energyPool = pool;
		pool.registerObserver(this);
	}

	public IWeapon getWeapon()
	{
		return weapon;
	}
}
