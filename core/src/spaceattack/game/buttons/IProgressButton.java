package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.utils.vector.IVector;

public interface IProgressButton extends IActor
{
	public void setJoystickPosition(float percentX, float percentY);

	public IVector screenToStageCoordinates(IVector touch);

	public void setGameActor(IGameActor gameActor);

	public boolean isOver();

	public boolean wasNotReleased();

	public void keep();

	public void release();
}
