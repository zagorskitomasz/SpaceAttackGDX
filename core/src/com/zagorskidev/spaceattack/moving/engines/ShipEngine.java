package com.zagorskidev.spaceattack.moving.engines;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.IShip.Turn;

public class ShipEngine implements IEngine
{
	private IShip ship;

	private float factorSpeed;
	private float factorAcceleration;
	private float factorBraking;
	private float factorAgility;

	private float baseSpeed;
	private float acceleration;
	private float braking;
	private float agility;

	private Vector2 destination;
	private Vector2 nextDestination;
	private float currentSpeed;

	private boolean isTurning;

	private Lock lock;

	ShipEngine(IShip ship)
	{
		this.ship = ship;
		lock = new ReentrantLock();
	}

	void setBaseSpeed(float factorSpeed)
	{
		this.factorSpeed = factorSpeed;
	}

	void setAcceleration(float acceleration)
	{
		this.factorAcceleration = acceleration;
	}

	void setBraking(float braking)
	{
		this.factorBraking = braking;
	}

	void setAgility(float agility)
	{
		this.factorAgility = agility;
	}

	@Override
	public void setLevel(int level)
	{
		baseSpeed = factorSpeed * (0.8f + 0.2f * level);
		acceleration = factorAcceleration * (0.8f + 0.2f * level);
		braking = factorBraking * (0.8f + 0.2f * level);
		agility = factorAgility * (0.8f + 0.2f * level);

		currentSpeed = baseSpeed;
	}

	@Override
	public void setDestination(Vector2 destination)
	{
		try
		{
			lock.lock();

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
		finally
		{
			lock.unlock();
		}
	}

	private boolean isDestinationReached()
	{
		return ship.getX() == this.destination.x && ship.getY() == this.destination.y;
	}

	@Override
	public Turn fly()
	{
		try
		{
			lock.lock();

			if (destination == null)
				return IShip.Turn.FRONT;

			if (isDestinationReached())
			{
				if (isTurning)
					doTurn();

				return IShip.Turn.FRONT;
			}

			computeSpeed();
			return moveShip();
		}
		finally
		{
			lock.unlock();
		}
	}

	private void doTurn()
	{
		destination = nextDestination;
		nextDestination = null;
		isTurning = false;
	}

	private Turn moveShip()
	{
		Vector2 movement = computeMovement();

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

	Vector2 computeMovement()
	{
		return new Vector2(destination.x - ship.getX(), destination.y - ship.getY());
	}

	private Turn calculateShipTurning(Vector2 movement)
	{
		if (movement.x >= 0.3)
			return Turn.RIGHT;

		if (movement.x <= -0.3)
			return Turn.LEFT;

		return Turn.FRONT;
	}

	private void computeSpeed()
	{
		Vector2 movement = computeMovement();

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
