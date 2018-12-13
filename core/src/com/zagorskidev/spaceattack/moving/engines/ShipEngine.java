package com.zagorskidev.spaceattack.moving.engines;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.IShip.Turn;

public class ShipEngine implements IEngine
{
	private IShip ship;

	private float baseSpeed;
	private float acceleration;
	private float braking;
	private float agility;

	private Vector2 destination;
	private Vector2 nextDestination;
	private float currentSpeed;

	private boolean isTurning;

	ShipEngine(IShip ship)
	{
		this.ship = ship;
	}

	void setBaseSpeed(float baseSpeed)
	{
		this.baseSpeed = baseSpeed;
		currentSpeed = baseSpeed;
	}

	void setAcceleration(float acceleration)
	{
		this.acceleration = acceleration;
	}

	void setBraking(float braking)
	{
		this.braking = braking;
	}

	void setAgility(float agility)
	{
		this.agility = agility;
	}

	@Override
	public void setDestination(Vector2 destination)
	{
		if (this.destination == null || isDestinationReached())
		{
			this.destination = destination;
		}
		else
		{
			isTurning = true;
			nextDestination = destination;
		}
	}

	private boolean isDestinationReached()
	{
		return ship.getX() == this.destination.x && ship.getY() == this.destination.y;
	}

	@Override
	public Turn fly()
	{
		if (destination == null)
			return IShip.Turn.FRONT;

		if (isDestinationReached())
		{
			if (isTurning)
				doTurn();

			return IShip.Turn.FRONT;
		}

		Vector2 movement = new Vector2(destination.x - ship.getX(), destination.y - ship.getY());

		computeSpeed(movement);
		return moveShip(movement);
	}

	private void doTurn()
	{
		destination = nextDestination;
		nextDestination = null;
		isTurning = false;
	}

	private Turn moveShip(Vector2 movement)
	{
		if (movement.len() <= currentSpeed || movement.len() <= baseSpeed)
		{
			ship.setX(destination.x);
			ship.setY(destination.y);

			return IShip.Turn.FRONT;
		}
		else
		{
			movement = movement.nor();
			ship.setX(ship.getX() + movement.x * currentSpeed);
			ship.setY(ship.getY() + movement.y * currentSpeed);

			return calculateShipTurning(movement);
		}
	}

	private Turn calculateShipTurning(Vector2 movement)
	{
		if (movement.x >= 0.3)
			return Turn.RIGHT;

		if (movement.x <= -0.3)
			return Turn.LEFT;

		return Turn.FRONT;
	}

	private void computeSpeed(Vector2 movement)
	{
		if (isTurning)
		{
			if (canTurnWithThisSpeed(movement.cpy().nor()))
				doTurn();
			else
				brake();
		}
		else
		{
			if (movement.len() > brakingWay())
				accelerate();
			else
				brake();

			if (currentSpeed <= baseSpeed)
				currentSpeed = baseSpeed;
		}
	}

	private void accelerate()
	{
		currentSpeed += acceleration;
	}

	private void brake()
	{
		currentSpeed -= braking;
	}

	private boolean canTurnWithThisSpeed(Vector2 currentDirection)
	{
		Vector2 newDirection = new Vector2(nextDestination.x - ship.getX(), nextDestination.y - ship.getY()).nor();

		float angle = computeAngle(currentDirection, newDirection);

		return currentSpeed <= baseSpeed || (currentSpeed * currentSpeed / agility) < angle;
	}

	private float computeAngle(Vector2 first,Vector2 second)
	{
		float angle = (MathUtils.atan2(first.x, first.y) - MathUtils.atan2(second.x, second.y));

		angle *= MathUtils.radiansToDegrees;
		angle = Math.abs(Math.abs(angle) - 180);

		return angle;
	}

	private float brakingWay()
	{
		float brakingWay = 0;
		float tempSpeed = currentSpeed - 1;

		while (tempSpeed >= baseSpeed)
		{
			brakingWay += tempSpeed;
			tempSpeed -= braking;
		}
		return brakingWay;
	}

	Vector2 getDestination()
	{
		return destination;
	}

	Vector2 getNextDestination()
	{
		return nextDestination;
	}

	float getCurrentSpeed()
	{
		return currentSpeed;
	}
}
