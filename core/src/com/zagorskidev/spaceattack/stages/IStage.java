package com.zagorskidev.spaceattack.stages;

public interface IStage
{
	public Stages getType();

	public boolean isCompleted();

	public Stages getResult();

	public void act(float delta);

	public void draw();
}
