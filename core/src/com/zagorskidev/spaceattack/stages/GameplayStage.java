package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zagorskidev.spaceattack.input.IInput;
import com.zagorskidev.spaceattack.input.MissionInputHandler;
import com.zagorskidev.spaceattack.notifiers.IObserver;
import com.zagorskidev.spaceattack.ships.ExperiencePool;
import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.player.PlayerShipFactory;
import com.zagorskidev.spaceattack.stages.impl.Bar;
import com.zagorskidev.spaceattack.stages.impl.ExperienceBar;
import com.zagorskidev.spaceattack.stages.impl.TimeLabel;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;
import com.zagorskidev.spaceattack.ui.buttons.FireButtonsFactory;
import com.zagorskidev.spaceattack.weapons.IMissileLauncher;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.IWeaponController;
import com.zagorskidev.spaceattack.weapons.MissileLauncher;
import com.zagorskidev.spaceattack.weapons.missiles.Killable;

public abstract class GameplayStage extends AbstractStage implements IWeaponController,IObserver<GameProgress>
{
	private IMissileLauncher missileLauncher;
	private IInput inputHandler;
	private IShip playersShip;
	private IPool expPool;

	private IWeapon primaryWeapon;
	private IWeapon secondaryWeapon;
	private FireButton secondaryFireButton;

	private boolean gameOver;
	private boolean won;

	private TimeLabel levelUpLabel;
	private TimeLabel finalizeLabel;

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
		this.gameProgress = gameProgress;

		if (playersShip != null)
		{
			this.gameProgress.registerObserver(playersShip);
			playersShip.notify(this.gameProgress);
		}

		this.gameProgress.registerObserver(this);

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

	@Override
	public void setSecondaryWeapon(IWeapon weapon)
	{
		this.secondaryWeapon = weapon;

		if (inputHandler == null)
			inputHandler = initInputProcessor();

		secondaryFireButton = createSecondaryFireButton();
		inputHandler.registerFireButton(secondaryFireButton);
	}

	@Override
	public void updateSecondaryWeapon(IWeapon weapon)
	{
		secondaryWeapon = weapon;
		secondaryFireButton.setWeapon(secondaryWeapon);
	}

	FireButton createPrimaryFireButton()
	{
		return FireButtonsFactory.INSTANCE.primary(this, primaryWeapon);
	}

	FireButton createSecondaryFireButton()
	{
		return FireButtonsFactory.INSTANCE.secondary(this, secondaryWeapon);
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
	public Vector2 getSecondaryWeaponUsePlacement()
	{
		return new Vector2(playersShip.getX(),
				playersShip.getY() + playersShip.getHeight() * secondaryWeapon.getWeaponsMovementFactor());
	}

	@Override
	public Vector2 getWeaponMovement()
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
		if (gameOver && finalizeLabel == null)
			showFinalizeLabel();

		if (finalizeLabel != null && !finalizeLabel.isVisible())
			finalizeStage();

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

	void setGameOver(boolean gameOver)
	{
		this.gameOver = gameOver;
	}

	void setWon(boolean won)
	{
		this.won = won;
	}

	boolean isGameOver()
	{
		return gameOver;
	}

	private void showFinalizeLabel()
	{
		if (won)
			finalizeLabel = createCompletedLabel();
		else
			finalizeLabel = createFailedLabel();

		addActor(finalizeLabel);
		finalizeLabel.show();
	}

	TimeLabel createCompletedLabel()
	{
		TimeLabel label = new TimeLabel(" MISSION\nCOMPLETED!");
		label.initGdx(Color.GREEN);

		return label;
	}

	TimeLabel createFailedLabel()
	{
		TimeLabel label = new TimeLabel("MISSION\nFAILED!");
		label.initGdx(Color.RED);

		return label;
	}

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

	@Override
	public void notify(GameProgress state)
	{
		if (state.getLevel() > progressBackup.getLevel())
			showLevelUpLabel();
	}

	void showLevelUpLabel()
	{
		if (levelUpLabel == null)
			createLevelUpLabel();

		levelUpLabel.show();
	}

	private void createLevelUpLabel()
	{
		levelUpLabel = new TimeLabel("LEVEL UP!");
		levelUpLabel.initGdx(Color.GOLDENROD);
		addActor(levelUpLabel);
	}
}
