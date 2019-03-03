package spaceattack.game.engines;

import spaceattack.consts.Consts;
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

	float currentSpeedHorizontal;
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
		computeHorizontalSpeed();
		computeVerticalSpeed();
		move();
		
		return calculateTurn();
	}

	private void computeHorizontalSpeed()
	{
		float targetSpeed = calculateTargetSpeed(accelerator.getHorizontalAcceleration());
		if(targetSpeed > 0)
		{
			if(isNearRightEdge())
				slowDownRight();
			else
				accelerateRight(targetSpeed);

		}
		else if(targetSpeed < 0)
		{
			if(isNearLeftEdge())
				slowDownLeft();
			else
				accelerateLeft(targetSpeed);
		}
		else
		{
			if(currentSpeedHorizontal > 0)
				slowDownRight();
			else if (currentSpeedHorizontal < 0)
				slowDownLeft();
		}
	}

	private boolean isNearRightEdge()
	{
		return Sizes.GAME_WIDTH - Sizes.SIDE_MARGIN - ship.getX() <= brakingWay(currentSpeedHorizontal);
	}

	private void slowDownRight()
	{
		currentSpeedHorizontal = Math.max(currentSpeedHorizontal - acceleration, 0);
	}

	private boolean isNearLeftEdge()
	{
		return ship.getX() - Sizes.SIDE_MARGIN <= brakingWay(-1 * currentSpeedHorizontal);
	}

	private void slowDownLeft()
	{
		currentSpeedHorizontal = Math.min(currentSpeedHorizontal + acceleration, 0);
	}

	private void accelerateRight(float targetSpeed)
	{
		currentSpeedHorizontal = Math.min(currentSpeedHorizontal + acceleration, targetSpeed);
	}

	private void accelerateLeft(float targetSpeed)
	{
		currentSpeedHorizontal  = Math.max(currentSpeedHorizontal - acceleration, targetSpeed);
	}

	private void computeVerticalSpeed()
	{
		float targetSpeed = calculateTargetSpeed(accelerator.getVerticalAcceleration());
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
	}

	private float calculateTargetSpeed(float acceleration)
	{
		return acceleration * maxSpeed / 100;
	}

	private boolean isNearUpperEdge() 
	{
		return Sizes.GAME_HEIGHT - Sizes.UPPER_MARGIN - ship.getY() <= brakingWay(currentSpeedVertical);
	}

	private void slowDownForward() 
	{
		currentSpeedVertical = Math.max(currentSpeedVertical - acceleration, 0);
	}

	private boolean isNearLowerEdge() 
	{
		return ship.getY() - Sizes.DOWN_MARGIN <= brakingWay(-1 * currentSpeedVertical);
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
		if(currentSpeedHorizontal > Consts.Gameplay.SHIP_TURN_THRESHOLD)
			return Turn.RIGHT;

		if(currentSpeedHorizontal < -1 * Consts.Gameplay.SHIP_TURN_THRESHOLD)
			return Turn.LEFT;

		return Turn.FRONT;
	}

	private void move() 
	{
		ship.setY(ship.getY() + currentSpeedVertical);
		ship.setX(ship.getX() + currentSpeedHorizontal);
	}

	private float brakingWay(float currentSpeed)
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
