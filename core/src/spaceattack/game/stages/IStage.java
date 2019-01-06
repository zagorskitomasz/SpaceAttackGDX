package spaceattack.game.stages;

import java.util.List;

import spaceattack.game.actors.IGameActor;

public interface IStage
{
	public void addActorAtBegining(IGameActor actor);

	public void addActor(IGameActor actor);

	public List<IGameActor> getGameActors();

	public void removeActor(IGameActor actor);

	public void updateViewport(int width,int height,boolean centerCamera);

	public void draw();

	public void act(float delta);
}
