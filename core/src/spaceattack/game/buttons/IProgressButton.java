package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.utils.vector.IVector;

public interface IProgressButton extends IActor
{
	public void setPercent(float percent);

	public IVector screenToStageCoordinates(IVector touch);

	public void setGameActor(IGameActor gameActor);

	public boolean isOver();
}
