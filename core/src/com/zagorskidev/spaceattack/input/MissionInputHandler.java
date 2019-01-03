package com.zagorskidev.spaceattack.input;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;

public class MissionInputHandler extends InputAdapter implements IInput
{
	private IShip ship;
	private Set<FireButton> buttons;

	public MissionInputHandler()
	{
		buttons = new HashSet<>();
	}

	@Override
	public void registerShip(IShip ship)
	{
		this.ship = ship;
		IPool pool = ship.getEnergyPool();

		for (FireButton button : buttons)
			button.setEnergyPool(pool);
	}

	@Override
	public void registerFireButton(FireButton button)
	{
		buttons.add(button);
	}

	@Override
	public boolean touchDown(int screenX,int screenY,int pointer,int button)
	{
		for (FireButton fireButton : buttons)
		{
			fireButton.touchDown(screenX, screenY);

		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX,int screenY,int pointer,int button)
	{
		for (FireButton fireButton : buttons)
		{
			if (fireButton.touchUp(screenX, screenY))
				return true;
		}

		if (ship != null)
			ship.setDestination(new Vector2(screenX, Sizes.gameHeight() - screenY));

		return true;
	}
}
