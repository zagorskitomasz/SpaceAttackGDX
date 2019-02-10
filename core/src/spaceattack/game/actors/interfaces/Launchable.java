package spaceattack.game.actors.interfaces;

import java.util.List;

import spaceattack.game.actors.IGameActor;

public interface Launchable extends IGameActor
{
	public void setActors(List<IGameActor> actors);

	public void playSound();

	public void launched();
}
