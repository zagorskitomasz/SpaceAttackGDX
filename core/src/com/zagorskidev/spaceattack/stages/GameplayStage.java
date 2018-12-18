package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.InputProcessor;
import com.zagorskidev.spaceattack.input.IInput;
import com.zagorskidev.spaceattack.input.MissionInputHandler;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.player.PlayerShipFactory;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;
import com.zagorskidev.spaceattack.ui.buttons.FireButtonsFactory;
import com.zagorskidev.spaceattack.weapons.IMissileLauncher;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.IWeaponController;
import com.zagorskidev.spaceattack.weapons.MissileLauncher;

public abstract class GameplayStage extends AbstractStage implements IWeaponController
{
	private IMissileLauncher missileLauncher;
	private IInput inputHandler;
	private IShip playersShip;

	public GameplayStage()
	{
		missileLauncher = new MissileLauncher(this);
	}

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

		this.gameProgress.registerObserver(playersShip);
		this.gameProgress.setLevel(this.gameProgress.getLevel());
	}

	@Override
	public void setPrimaryWeapon(IWeapon weapon)
	{
		if (inputHandler == null)
			inputHandler = initInputProcessor();

		FireButton primaryFireButton = FireButtonsFactory.INSTANCE.primary(this, weapon);
		inputHandler.registerFireButton(primaryFireButton);
	}

	public IMissileLauncher getMissileLauncher()
	{
		return missileLauncher;
	}
}
