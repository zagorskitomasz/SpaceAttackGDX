package spaceattack.game.stages.impl;

import java.util.LinkedList;
import java.util.List;

import spaceattack.game.actors.IGameActor;
import spaceattack.game.stages.IStage;

public class FakeStage implements IStage
{
	private List<IGameActor> actors;

	public FakeStage()
	{
		actors = new LinkedList<>();
	}

	@Override
	public void addActorAtBegining(IGameActor actor)
	{
		actors.add(Math.min(1, actors.size()), actor);
	}

	@Override
	public void addActor(IGameActor actor)
	{
		actors.add(actor);
	}

	@Override
	public List<IGameActor> getGameActors()
	{
		return actors;
	}

	@Override
	public void removeActor(IGameActor actor)
	{
		actors.remove(actor);
	}

	@Override
	public void updateViewport(int width,int height,boolean centerCamera)
	{
		// do nothing
	}

	@Override
	public void draw()
	{
		// do nothing
	}

	@Override
	public void act(float delta)
	{
		actors.forEach(actor->actor.act(delta));
	}

	@Override
	public void addBackground(IGameActor actor)
	{
		actors.add(0, actor);
	}
}
