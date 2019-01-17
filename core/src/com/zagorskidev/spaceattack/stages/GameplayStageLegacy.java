package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zagorskidev.spaceattack.ships.player.PlayerShipFactory;
import com.zagorskidev.spaceattack.weapons.IMissileLauncher;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.actors.TimeLabel;
import spaceattack.game.actors.interfaces.Killable;
import spaceattack.game.actors.interfaces.RequiredOnStage;
import spaceattack.game.bars.Bar;
import spaceattack.game.bars.ExperienceBar;
import spaceattack.game.buttons.weapon.FireButton;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.pools.ExperiencePool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.AbstractStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.notifiers.IObserver;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.MissilesLauncher;

public abstract class GameplayStageLegacy extends AbstractStage implements IObserver<GameProgress>
{
	private IMissileLauncher missileLauncher;
	private IShip playersShip;
	private IPool expPool;

	private IWeapon primaryWeapon;
	private IWeapon secondaryWeapon;
	private FireButton secondaryFireButton;

	private boolean gameOver;
	private boolean won;

	private TimeLabel levelUpLabel;
	private TimeLabel finalizeLabel;

	public GameplayStageLegacy()
	{
		missileLauncher = new MissilesLauncher(this);
	}

	protected IShip createPlayersShip()
	{
		playersShip = PlayerShipFactory.INSTANCE.create(this);
		registerShip(playersShip);
		return playersShip;
	}

	void registerShip(IShip ship)
	{
		inputHandler.registerShip(ship);
		playersShip = ship;
	}

	void initExpBar()
	{
		expPool = new ExperiencePool(getGameProgress(), getProgressBackup());
		Bar expBar = new ExperienceBar(expPool);
		expBar.initGdx();
		addActor(expBar);
	}

	public IMissileLauncher getMissileLauncher()
	{
		return missileLauncher;
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
		// TODO stage.removeActor();
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
