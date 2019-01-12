package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;

public interface IButton extends IGameActor,IActor
{
	@Override
	public default void setActor(IActor actor)
	{
		// do nothing
	}

	@Override
	public default void draw(IBatch batch,float alpha)
	{
		// do nothing
	}

	public void setPosition(float x,float y);

	public void setSize(float buttonWidth,float buttonHeight);

	public void addListener(IListener listener);

	public void setEnabled(boolean enabled);

	public void setVisible(boolean visible);

	public void setColumnPosition(int position);

	public int getGridPosition();

	public void setText(String apply);
}
