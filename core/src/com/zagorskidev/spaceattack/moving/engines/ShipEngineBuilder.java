package com.zagorskidev.spaceattack.moving.engines;

import com.zagorskidev.spaceattack.ships.IShip;

public enum ShipEngineBuilder
{
	INSTANCE;

	public Builder getBuilder(IShip ship)
	{
		return new BuilderImpl(ship);
	}

	public static class BuilderImpl implements Builder,BaseSpeed,Acceleration,Braking,Agility,Level
	{
		private ShipEngine engine;

		private BuilderImpl(IShip ship)
		{
			engine = new ShipEngine(ship);
		}

		@Override
		public BaseSpeed setBaseSpeed(float baseSpeed)
		{
			engine.setBaseSpeed(baseSpeed);
			return this;
		}

		@Override
		public Acceleration setAcceleration(float acceleration)
		{
			engine.setAcceleration(acceleration);
			return this;
		}

		@Override
		public Braking setBraking(float braking)
		{
			engine.setBraking(braking);
			return this;
		}

		@Override
		public Agility setAgility(float agility)
		{
			engine.setAgility(agility);
			return this;
		}

		@Override
		public Level setEngineLevel(int level)
		{
			engine.setLevel(level);
			return this;
		}

		@Override
		public ShipEngine build()
		{
			return engine;
		}
	}

	public interface Builder
	{
		public BaseSpeed setBaseSpeed(float baseSpeed);
	}

	public interface BaseSpeed
	{
		public Acceleration setAcceleration(float acceleration);
	}

	public interface Acceleration
	{
		public Braking setBraking(float braking);
	}

	public interface Braking
	{
		public Agility setAgility(float agility);
	}

	public interface Agility
	{
		public Level setEngineLevel(int level);
	}

	public interface Level
	{
		public ShipEngine build();
	}
}
