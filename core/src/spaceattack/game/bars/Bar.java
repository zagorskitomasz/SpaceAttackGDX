package spaceattack.game.bars;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.ITexture;

public abstract class Bar implements IGameActor
{
	protected ITexture texture;

	private IActor actor;

	@Override
	public void draw(IBatch batch,float aplpha)
	{
		drawBarRect(batch);
		drawFillerLabel(batch);
		if (texture != null)
			drawTexture(batch);
	}

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

	protected abstract void drawBarRect(IBatch batch);

	protected abstract void drawFillerLabel(IBatch batch);

	protected abstract void drawTexture(IBatch batch);

	@Override
	public void act(float delta)
	{
		// do nothing
	}
}
