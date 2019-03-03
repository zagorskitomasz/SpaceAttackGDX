package spaceattack.game.engines;

import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.vector.IVectorFactory;

public abstract class AbstractShipEngine implements IEngine
{
	protected IVectorFactory vectorFactory;

	protected IShip ship;

	protected float factorSpeed;
	protected float factorAcceleration;
	protected float factorBraking;
	protected float factorAgility;

	protected float baseSpeed;
	protected float acceleration;
	protected float braking;
	protected float agility;

	protected float currentSpeed;
	
	public AbstractShipEngine(IShip ship) 
	{
		this.ship = ship;
		vectorFactory = Factories.getVectorFactory();
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
}
