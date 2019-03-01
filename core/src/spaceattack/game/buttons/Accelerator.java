package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;
import spaceattack.game.input.IGameplayInput;
import spaceattack.game.utils.vector.IVector;

public class Accelerator implements IGameActor 
{
	private IProgressButton button;
	private IGameplayInput processor;
	private float percent;
	
	@Override
	public IActor getActor() 
	{
		return button;
	}

	public void setProgressButton(IProgressButton progressButton) 
	{
		button = progressButton;
	}

	public void setPosition(float x, float y) 
	{
		button.setX(x);
		button.setY(y);
	}

	public void setInputProcessor(IGameplayInput processor) 
	{
		this.processor = processor;
	}

	@Override
	public void act(float delta) 
	{
		IVector touch = processor.getTouch();
		if(touch == null)
		{
			percent = 50;
		}
		else
		{
			IVector screenTouch = button.screenToStageCoordinates(touch);
			if(isButtonPressed(screenTouch))
				percent = (screenTouch.getY() - button.getY()) * 100 / button.getHeight();
			else
				percent = 50;
		}
		button.setPercent(percent);
	}

	private boolean isButtonPressed(IVector screenTouch) 
	{
		return screenTouch.getX() >= button.getX() && screenTouch.getX() <= button.getX() + button.getWidth() && screenTouch.getY() >= button.getY() && screenTouch.getY() <= button.getY() + button.getHeight();
	}
	
	public float getAcceleration()
	{
		return percent * 2 - 100;
	}

	@Override
	public void setActor(IActor actor) 
	{
		// do nothing
	}

	@Override
	public void draw(IBatch batch, float alpha) 
	{
		// do nothing
	}
}
