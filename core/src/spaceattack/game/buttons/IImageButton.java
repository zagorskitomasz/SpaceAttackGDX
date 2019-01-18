package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.input.InputType;
import spaceattack.game.utils.vector.IVector;

public interface IImageButton extends IActor
{
	@Override
	public void setX(float x);

	@Override
	public void setY(float y);

	@Override
	public float getWidth();

	@Override
	public float getHeight();

	public void fire(InputType type);

	public boolean isPressed();

	public IVector screenToStageCoordinates(IVector touch);

	public boolean isDisabled();

	public void setEnabled(boolean b);
}
