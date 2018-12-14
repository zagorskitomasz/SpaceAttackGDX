package com.zagorskidev.spaceattack.input;

import com.badlogic.gdx.InputProcessor;
import com.zagorskidev.spaceattack.ships.IShip;

public interface IInput extends InputProcessor
{
	public void registerShip(IShip ship);
}
