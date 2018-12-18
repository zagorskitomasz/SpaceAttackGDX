package com.zagorskidev.spaceattack.input;

import com.badlogic.gdx.InputProcessor;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;

public interface IInput extends InputProcessor
{
	public void registerShip(IShip ship);

	void registerFireButton(FireButton button);
}
