package spaceattack.ext.stage;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import spaceattack.ext.actor.GdxActor;
import spaceattack.ext.batch.BatchProxyHolder;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.stages.IStage;

public class GdxStage extends Stage implements IStage
{
	private List<IGameActor> actorProxies;

	public GdxStage()
	{
		actorProxies = new LinkedList<>();
	}

	@Override
	public void addActorAtBegining(IGameActor actor)
	{
		actorProxies.add(0, actor);
		getRoot().addActorAt(0, (GdxActor) actor.getActor());
	}

	@Override
	public void updateViewport(int width,int height,boolean centerCamera)
	{
		super.getViewport().update(width, height, centerCamera);
	}

	@Override
	public void addActor(IGameActor actor)
	{
		actorProxies.add(actor);
		super.addActor((Actor) actor.getActor());
	}

	@Override
	public List<IGameActor> getGameActors()
	{
		return actorProxies;
	}

	@Override
	public void removeActor(IGameActor actor)
	{
		actorProxies.remove(actor);
		getActors().removeValue((GdxActor) actor.getActor(), false);
	}

	@Override
	public void draw()
	{
		BatchProxyHolder.INSTANCE.set(getBatch());
		super.draw();
	}
}