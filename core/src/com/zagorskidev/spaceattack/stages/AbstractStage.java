package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zagorskidev.spaceattack.graphics.StaticImageFactory;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.system.GameSaver;
import com.zagorskidev.spaceattack.ui.ActorGUI;

public abstract class AbstractStage extends Stage implements IStage
{
	protected Stages type;
	private StageResult result;
	protected GameProgress gameProgress;
	protected GameProgress progressBackup;

	@Override
	public boolean isCompleted()
	{
		return result != null;
	}

	@Override
	public StageResult getResult()
	{
		return result;
	}

	@Override
	public void setResult(StageResult result,boolean forceSave)
	{
		this.result = result;
		if (result.getGameProgress() != null && (!result.getGameProgress().equals(progressBackup) || forceSave))
			saveProgress(GameSaver.INSTANCE, result.getGameProgress());
	}

	@Override
	public Stages getType()
	{
		return type;
	}

	@Override
	public void setType(Stages type)
	{
		this.type = type;
	}

	@Override
	public GameProgress getGameProgress()
	{
		if (gameProgress == null)
			gameProgress = new GameProgress();

		return gameProgress;
	}

	@Override
	public void setGameProgress(GameProgress gameProgress)
	{
		this.gameProgress = gameProgress;

		if (progressBackup == null)
			progressBackup = gameProgress;
	}

	@Override
	public void saveProgress(GameSaver saver,GameProgress gameProgress)
	{
		saver.save(gameProgress);
	}

	protected Actor createImage(Texture texture,float x,float y)
	{
		return StaticImageFactory.INSTANCE.create(texture, x, y);
	}

	@Override
	public void addActorBeforeGUI(Actor newActor)
	{
		for (Actor actor : getActors())
		{
			if (actor instanceof ActorGUI)
			{
				getRoot().addActorBefore(actor, newActor);
				return;
			}
		}
	}

	protected GameProgress getProgressBackup()
	{
		System.out.println(progressBackup.getExperience());
		return progressBackup;
	}
}
