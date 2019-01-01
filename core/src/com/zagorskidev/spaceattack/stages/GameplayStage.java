package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zagorskidev.spaceattack.input.IInput;
import com.zagorskidev.spaceattack.input.MissionInputHandler;
import com.zagorskidev.spaceattack.ships.ExperiencePool;
import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.player.PlayerShipFactory;
import com.zagorskidev.spaceattack.stages.impl.Bar;
import com.zagorskidev.spaceattack.stages.impl.ExperienceBar;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;
import com.zagorskidev.spaceattack.ui.buttons.FireButtonsFactory;
import com.zagorskidev.spaceattack.weapons.IMissileLauncher;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.IWeaponController;
import com.zagorskidev.spaceattack.weapons.MissileLauncher;
import com.zagorskidev.spaceattack.weapons.missiles.Killable;

public abstract class GameplayStage extends AbstractStage implements IWeaponController,IGameOverChecker
{
	private IMissileLauncher missileLauncher;
	private IInput inputHandler;
	private IShip playersShip;
	private IPool expPool;

	private IWeapon primaryWeapon;

	private boolean gameOver;
	private boolean won;

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
		IInput input = new MissionInputHandler(this);
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
		this.gameProgress = gameProgress;
		this.gameProgress.registerObserver(playersShip);
		this.gameProgress.setLevel(this.gameProgress.getLevel());

		if (progressBackup == null)
		{
			progressBackup = this.gameProgress.clone();
			initExpBar();
		}
	}

	void initExpBar()
	{
		expPool = new ExperiencePool(getGameProgress(), getProgressBackup());
		Bar expBar = new ExperienceBar(expPool);
		expBar.initGdx();
		addActor(expBar);
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

	@Override
	public boolean takeEnergy(float energyCost)
	{
		return playersShip.takeEnergy(energyCost);
	}

	@Override
	public void act(float delta)
	{
		callSuperAct(delta);

		Array<Actor> actorsToKill = new Array<>();
		getActors().forEach(actor->
		{
			if (actor instanceof Killable && ((Killable) actor).isToKill())
			{
				actorsToKill.add(actor);
				if (actor instanceof RequiredOnStage)
					lose();
			}
		});

		getActors().removeAll(actorsToKill, true);
	}

	void callSuperAct(float delta)
	{
		super.act(delta);
	}

	void lose()
	{
		gameOver = true;
		if (expPool != null)
			expPool.destroy();
	}

	@Override
	public boolean isGameOver()
	{
		return gameOver;
	}

	void setGameOver(boolean gameOver)
	{
		this.gameOver = gameOver;
	}

	void setWon(boolean won)
	{
		this.won = won;
	}

	@Override
	public void finalizeStage()
	{
		StageResult result = new StageResult();
		result.setNextStage(Stages.MISSIONS);

		if (won)
		{
			result.setGameProgress(getGameProgress());
		}
		else
		{
			result.setGameProgress(getProgressBackup());
		}

		setResult(result, false);
	}
}
