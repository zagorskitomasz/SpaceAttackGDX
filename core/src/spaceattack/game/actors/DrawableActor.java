package spaceattack.game.actors;

import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.ITexture;

public abstract class DrawableActor implements IGameActor
{
	protected abstract ITexture getTexture();

	private IActor actor;

	@Override
	public void setActor(IActor actor)
	{
		this.actor = actor;
	}

	@Override
	public IActor getActor()
	{
		return actor;
	}

	@Override
	public void draw(IBatch batch,float alpha)
	{
		if (getTexture() != null)
			batch.draw(getTexture(), getDrawingX(), getDrawingY());
	}

	private float getDrawingX()
	{
		return actor.getX() - getTexture().getWidth() * 0.5f;
	}

	private float getDrawingY()
	{
		return actor.getY() - getTexture().getHeight() * 0.5f;
	}
}
