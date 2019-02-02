package spaceattack.game.actors;

import spaceattack.game.batch.IBatch;

public abstract class InvisibleActor implements IGameActor
{
	private IActor actor;

	@Override
	public IActor getActor()
	{
		return actor;
	}

	@Override
	public void setActor(IActor actor)
	{
		this.actor = actor;
	}

	@Override
	public void draw(IBatch batch,float alpha)
	{
		// do nothing
	}
}
