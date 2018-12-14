package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.InputProcessor;
import com.zagorskidev.spaceattack.input.IInput;
import com.zagorskidev.spaceattack.input.MissionInputHandler;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.player.PlayerShipFactory;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.IWeaponController;

public abstract class GameplayStage extends AbstractStage implements IWeaponController
{
	private IInput inputHandler;

	protected IShip createPlayersShip()
	{
		IShip ship = PlayerShipFactory.INSTANCE.create(this);
		registerShip(ship);
		return ship;
	}

	void registerShip(IShip ship)
	{
		if (inputHandler == null)
			inputHandler = initInputProcessor();

		inputHandler.registerShip(ship);
	}

	IInput initInputProcessor()
	{
		IInput input = new MissionInputHandler();
		return input;
	}

	@Override
	public InputProcessor getInputProcessor()
	{
		if (inputHandler == null)
			inputHandler = initInputProcessor();

		return inputHandler;
	}

	@Override
	public void setPrimaryWeapon(IWeapon weapon)
	{
		// TODO
	}
}
