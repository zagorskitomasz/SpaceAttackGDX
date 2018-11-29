package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public interface IStage
{
	public Stages getType();

	public boolean isCompleted();

	public Stages getResult();

	public void act(float delta);

	public void draw();

	public Array<Actor> getActors();

	public void setType(Stages type);

	public void setResult(Stages result);
}
