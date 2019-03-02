package spaceattack.game.engines;

import spaceattack.consts.Sizes;
import spaceattack.game.buttons.IAccelerator;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.IShip.Turn;
import spaceattack.game.utils.vector.IVector;

public class InputShipEngine extends AbstractShipEngine
{
	private float maxSpeed;
	private float factorMaxSpeed;
	
	private IAccelerator accelerator;
	
	float currentSpeedVertical;
	
	public InputShipEngine(IShip ship) 
	{
		super(ship);
	}

	public void setMaxSpeed(float maxSpeed) 
	{
		factorMaxSpeed = maxSpeed;
	}

	public void setAccelerator(IAccelerator accelerator) 
	{
		this.accelerator = accelerator;
	}

	@Override
	public Turn fly() 
	{
		float targetSpeed = calculateTargetSpeed();
		
		if(targetSpeed > 0)
		{
			if(isNearUpperEdge())
				slowDownForward();
			else
				accelerateForward(targetSpeed);
				
		}
		else if(targetSpeed < 0)
		{
			if(isNearLowerEdge())
				slowDownBackward();
			else
				accelerateBackward(targetSpeed);
		}
		else
		{
			if(currentSpeedVertical > 0)
				slowDownForward();
			else if (currentSpeedVertical < 0)
				slowDownBackward();
		}
		move();
		
		return calculateTurn();
	}

	private float calculateTargetSpeed() 
	{
		return accelerator.getVerticalAcceleration() * maxSpeed / 100;
	}

	private boolean isNearUpperEdge() 
	{
		return Sizes.GAME_HEIGHT - Sizes.UPPER_MARGIN - ship.getY() <= brakeingWay(currentSpeedVertical);
	}

	private void slowDownForward() 
	{
		currentSpeedVertical = Math.max(currentSpeedVertical - acceleration, 0);
	}

	private boolean isNearLowerEdge() 
	{
		return ship.getY() - Sizes.DOWN_MARGIN <= brakeingWay(-1 * currentSpeedVertical);
	}

	private void slowDownBackward() 
	{
		currentSpeedVertical = Math.min(currentSpeedVertical + acceleration, 0);
	}

	private void accelerateForward(float targetSpeed) 
	{
		currentSpeedVertical = Math.min(currentSpeedVertical + acceleration, targetSpeed);
	}

	private void accelerateBackward(float targetSpeed) 
	{
		currentSpeedVertical  = Math.max(currentSpeedVertical - acceleration, targetSpeed);
	}

	private Turn calculateTurn() 
	{
		return Turn.FRONT;
	}

	private void move() 
	{
		ship.setY(ship.getY() + currentSpeedVertical);
	}

	private float brakeingWay(float currentSpeed)
	{
		float brakingWay = 0;
		float tempSpeed = currentSpeed;

		while (tempSpeed >= 0)
		{
			brakingWay += tempSpeed;
			tempSpeed -= acceleration;
		}
		return brakingWay;
	}

	@Override
	public boolean isDestinationReached() 
	{
		return currentSpeed == 0;
	}
	
	@Override
	public void setLevel(int level)
	{
		super.setLevel(level);
		maxSpeed = factorMaxSpeed * (0.8f + 0.2f * level);
	}

	@Override
	public void setDestination(IVector destination) 
	{
		// do nothing
	}
}
