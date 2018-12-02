package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zagorskidev.spaceattack.system.GameLoader;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.system.GameSaver;

public interface IStage
{
	public Stages getType();

	public boolean isCompleted();

	public StageResult getResult();

	public void act(float delta);

	public void draw();

	public Array<Actor> getActors();

	public void setType(Stages type);

	public void setResult(StageResult result,boolean forceSave);

	public GameProgress getGameProgress();

	public void setGameProgress(GameProgress gameProgress);

	default public void loadGame(GameLoader loader)
	{
		throw new UnsupportedOperationException(
				"Loading game is not supported on stage: " + getClass().getSimpleName());
	}

	void saveProgress(GameSaver saver);

	public Viewport getViewport();
}
