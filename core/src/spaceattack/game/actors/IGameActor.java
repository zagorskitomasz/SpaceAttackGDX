package spaceattack.game.actors;

import spaceattack.game.batch.IBatch;

public interface IGameActor
{
	public IActor getActor();

	public void setActor(IActor actor);

	public void act(float delta);

	public void draw(IBatch batch,float alpha);
}
