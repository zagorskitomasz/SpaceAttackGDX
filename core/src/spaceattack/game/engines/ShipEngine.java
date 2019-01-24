package spaceattack.game.engines;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.IShip.Turn;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class ShipEngine implements IEngine
{
	private IUtils utils;
	private IVectorFactory vectorFactory;

	private IShip ship;

	private float factorSpeed;
	private float factorAcceleration;
	private float factorBraking;
	private float factorAgility;

	private float baseSpeed;
	private float acceleration;
	private float braking;
	private float agility;

	private IVector destination;
	private IVector nextDestination;
	private float currentSpeed;

	private boolean isTurning;

	private Lock lock;

	ShipEngine(IShip ship,IUtils utils)
	{
		this.ship = ship;
		this.utils = utils;
		vectorFactory = Factories.getVectorFactory();
		lock = new ReentrantLock();
	}

	@Override
	public void setBaseSpeed(float factorSpeed)
	{
		this.factorSpeed = factorSpeed;
	}

	@Override
	public void setAcceleration(float acceleration)
	{
		this.factorAcceleration = acceleration;
	}

	@Override
	public void setBraking(float braking)
	{
		this.factorBraking = braking;
	}

	@Override
	public void setAgility(float agility)
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
	public void setDestination(IVector destination)
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
		return ship.getX() == destination.getX() && ship.getY() == destination.getY();
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
		IVector movement = computeMovement();

		if (movement.length() <= currentSpeed || movement.length() <= baseSpeed)
		{
			ship.setX(destination.getX());
			ship.setY(destination.getY());

			return IShip.Turn.FRONT;
		}
		else
		{
			movement = movement.normalize();
			ship.setX(ship.getX() + movement.getX() * currentSpeed);
			ship.setY(ship.getY() + movement.getY() * currentSpeed);

			return calculateShipTurning(movement);
		}
	}

	IVector computeMovement()
	{
		return vectorFactory.create(destination.getX() - ship.getX(), destination.getY() - ship.getY());
	}

	private Turn calculateShipTurning(IVector movement)
	{
		if (movement.getX() >= 0.3)
			return Turn.RIGHT;

		if (movement.getX() <= -0.3)
			return Turn.LEFT;

		return Turn.FRONT;
	}

	private void computeSpeed()
	{
		IVector movement = computeMovement();

		if (isTurning)
		{
			if (canTurnWithThisSpeed(movement.copy().normalize()))
				doTurn();
			else
				brake();
		}
		else
		{
			if (movement.length() > brakingWay())
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

	private boolean canTurnWithThisSpeed(IVector currentDirection)
	{
		IVector newDirection = vectorFactory
				.create(nextDestination.getX() - ship.getX(), nextDestination.getY() - ship.getY()).normalize();

		float angle = computeAngle(currentDirection, newDirection);

		return currentSpeed <= baseSpeed || (currentSpeed * currentSpeed / agility) < angle;
	}

	private float computeAngle(IVector first,IVector second)
	{
		float angle = (utils.atan2(first.getX(), first.getY()) - utils.atan2(second.getX(), second.getY()));

		angle *= utils.radiansToDegrees();
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

	IVector getDestination()
	{
		return destination;
	}

	IVector getNextDestination()
	{
		return nextDestination;
	}

	float getCurrentSpeed()
	{
		return currentSpeed;
	}
}
