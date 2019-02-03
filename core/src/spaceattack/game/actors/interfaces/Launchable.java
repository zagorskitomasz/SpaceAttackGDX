package spaceattack.game.actors.interfaces;

import spaceattack.game.actors.IGameActor;

public interface Launchable extends IGameActor
{
	public void setActors(Iterable<IGameActor> actors);

	public void playSound();
}
