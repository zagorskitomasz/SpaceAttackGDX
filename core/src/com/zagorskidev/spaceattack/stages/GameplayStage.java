package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.InputProcessor;
import com.zagorskidev.spaceattack.input.IInput;
import com.zagorskidev.spaceattack.input.MissionInputHandler;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.player.PlayerShipFactory;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.IWeaponController;

public abstract class GameplayStage extends AbstractStage implements IWeaponController
{
	private IInput inputHandler;
	private IShip playersShip;

	protected IShip createPlayersShip()
	{
		playersShip = PlayerShipFactory.INSTANCE.create(this);
		registerShip(playersShip);
		return playersShip;
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
	public void setGameProgress(GameProgress gameProgress)
	{
		super.setGameProgress(gameProgress);

		playersShip.setLevel(gameProgress.getLevel());
	}

	@Override
	public void setPrimaryWeapon(IWeapon weapon)
	{
		// TODO
	}
}
