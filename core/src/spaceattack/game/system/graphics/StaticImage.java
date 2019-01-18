package spaceattack.game.system.graphics;

import spaceattack.consts.Sizes;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;

public class StaticImage implements IGameActor
{
	private IActor actor;

	private float x;
	private float y;

	private ITexture texture;

	StaticImage(ITexture texture,float x,float y)
	{
		this.texture = texture;
		this.x = x;
		this.y = Sizes.GAME_HEIGHT - (y + texture.getHeight());
	}

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
		batch.draw(texture, x, y);
	}

	@Override
	public void act(float delta)
	{
		// do nothing
	}

	public void setTexture(ITexture newTexture)
	{
		texture = newTexture;
	}

	ITexture getTexture()
	{
		return texture;
	}

	float getX()
	{
		return x;
	}

	float getY()
	{
		return y;
	}
}
