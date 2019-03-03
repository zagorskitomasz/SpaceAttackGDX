package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.input.InputType;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;

public interface IImageButton extends IActor
{
	public void fire(InputType type);

	public boolean isPressed();

	public IVector screenToStageCoordinates(IVector touch);

	public boolean isDisabled();

	public void setEnabled(boolean b);

	public void setDown(ITexture texture);

	public void setUp(ITexture texture);

	public void setGameActor(IGameActor gameActor);
}
