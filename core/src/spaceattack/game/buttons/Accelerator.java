package spaceattack.game.buttons;

import spaceattack.consts.Consts;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;
import spaceattack.game.input.IGameplayInput;
import spaceattack.game.utils.vector.IVector;

public class Accelerator implements IGameActor, IAccelerator 
{
	private IProgressButton button;
	private IGameplayInput processor;
	private float acceleratorControlPercent;
	private float acceleratorXAxis;
	
	public Accelerator() 
	{
		acceleratorControlPercent = 50;
	}
	
	@Override
	public IActor getActor() 
	{
		return button;
	}

	public void setProgressButton(IProgressButton progressButton) 
	{
		button = progressButton;
	}

	public void setInputProcessor(IGameplayInput processor) 
	{
		this.processor = processor;
	}

	@Override
	public void act(float delta) 
	{
		acceleratorXAxis = processor.getAccelerometrX();
		IVector touch = processor.getTouch();
		if(touch == null)
		{
			acceleratorControlPercent = 50;
			button.release();
		}
		else
		{
			IVector screenTouch = button.screenToStageCoordinates(touch);
			if(isButtonPressed(screenTouch))
				acceleratorControlPercent = (screenTouch.getY() - button.getY()) * 100 / button.getHeight();
			else if(button.wasNotReleased())
				acceleratorControlPercent = screenTouch.getY() <= button.getY() ? 0 : 100;
			else
				acceleratorControlPercent = 50;
		}
		button.setPercent(acceleratorControlPercent);
	}

	private boolean isButtonPressed(IVector screenTouch) 
	{
		boolean touched = screenTouch.getX() >= button.getX() && screenTouch.getX() <= button.getX() + button.getWidth() && screenTouch.getY() >= button.getY() && screenTouch.getY() <= button.getY() + button.getHeight();

		if(touched)
			button.keep();
		
		return touched;
	}
	
	@Override
	public float getVerticalAcceleration()
	{
		return acceleratorControlPercent * 2 - 100;
	}

	@Override
	public float getHorizontalAcceleration()
	{
		if(Math.abs(acceleratorXAxis) < Consts.Metagame.ACCELEROMETR_THRESHOLD)
			return 0;

		float turnPercent = acceleratorXAxis * 100 / Consts.Metagame.ACCELEROMETR_MAX * -1;

		if(turnPercent < -100)
			return -100;

		if(turnPercent > 100)
			return 100;

		return turnPercent;
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
