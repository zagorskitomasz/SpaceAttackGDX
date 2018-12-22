package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
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

	private IWeapon primaryWeapon;

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
		playersShip = ship;
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
		this.primaryWeapon = weapon;

		if (inputHandler == null)
			inputHandler = initInputProcessor();

		FireButton primaryFireButton = createPrimaryFireButton();
		inputHandler.registerFireButton(primaryFireButton);
	}

	FireButton createPrimaryFireButton()
	{
		return FireButtonsFactory.INSTANCE.primary(this, primaryWeapon);
	}

	public IMissileLauncher getMissileLauncher()
	{
		return missileLauncher;
	}

	@Override
	public Vector2 getPrimaryWeaponUsePlacement()
	{
		return new Vector2(playersShip.getX(),
				playersShip.getY() + playersShip.getHeight() * primaryWeapon.getWeaponsMovementFactor());
	}

	@Override
	public Vector2 getPrimaryWeaponMovement()
	{
		return new Vector2(0, 1);
	}
}
