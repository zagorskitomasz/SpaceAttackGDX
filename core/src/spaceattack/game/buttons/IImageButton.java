package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.input.InputType;
import spaceattack.game.utils.vector.IVector;

public interface IImageButton extends IActor
{
	public void setX(float x);

	public void setY(float y);

	public float getWidth();

	public float getHeight();

	public void fire(InputType type);

	public boolean isPressed();

	public IVector screenToStageCoordinates(IVector touch);

	public boolean isDisabled();

	public void setEnabled(boolean b);
}
