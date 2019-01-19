package spaceattack.game.stages.impl;

import java.util.LinkedList;
import java.util.List;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.TimeLabel;
import spaceattack.game.actors.interfaces.Killable;
import spaceattack.game.actors.interfaces.RequiredOnStage;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.AbstractStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.notifiers.IObserver;

public class GameplayStage extends AbstractStage implements IObserver<GameProgress>
{
	private boolean gameOver;
	private boolean won;

	private TimeLabel levelUpLabel;
	private TimeLabel completedLabel;
	private TimeLabel failedLabel;

	private TimeLabel finalizeLabel = null;

	private IPool expPool;

	void setExpPool(IPool pool)
	{
		expPool = pool;
	}

	void setLevelUpLabel(TimeLabel label)
	{
		levelUpLabel = label;
	}

	void setCompletedLabel(TimeLabel label)
	{
		completedLabel = label;
	}

	void setFailedLabel(TimeLabel label)
	{
		failedLabel = label;
	}

	@Override
	public IInputProcessor getInputProcessor()
	{
		return null;
	}

	@Override
	public void notify(GameProgress state)
	{
		if (state.getLevel() > getProgressBackup().getLevel())
			showLevelUpLabel();
	}

	void showLevelUpLabel()
	{
		if (!getActors().contains(levelUpLabel))
			addActor(levelUpLabel);

		levelUpLabel.show();
	}

	@Override
	public void act(float delta)
	{
		if (gameOver && finalizeLabel == null)
			showFinalizeLabel();

		if (finalizeLabel != null && !finalizeLabel.isVisible())
			finalizeStage();

		super.act(delta);

		List<IGameActor> actorsToKill = new LinkedList<>();
		getActors().forEach(actor->
		{
			if (actor instanceof Killable && ((Killable) actor).isToKill())
			{
				actorsToKill.add(actor);
				if (actor instanceof RequiredOnStage)
					lose();
			}
		});
		actorsToKill.forEach(actor->stage.removeActor(actor));
	}

	void lose()
	{
		gameOver = true;

		if (expPool != null)
			expPool.destroy();
	}

	private void showFinalizeLabel()
	{
		if (won)
			finalizeLabel = completedLabel;
		else
			finalizeLabel = failedLabel;

		addActor(finalizeLabel);
		finalizeLabel.show();
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
		setResult(result);
	}

	boolean isGameOver()
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
}
