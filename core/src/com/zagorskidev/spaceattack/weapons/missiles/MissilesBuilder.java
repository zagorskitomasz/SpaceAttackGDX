package com.zagorskidev.spaceattack.weapons.missiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public enum MissilesBuilder
{
	INSTANCE;

	public Builder init()
	{
		return new BuilderImpl();
	}

	public static class BuilderImpl
			implements Builder,TextureSet,DmgSet,SpeedSet,AccelerationSet,MovementSet,PositionSet
	{
		private Missile missile;

		private BuilderImpl()
		{
			missile = new Missile();
		}

		@Override
		public TextureSet setTexture(Texture texture)
		{
			missile.setTexture(texture);
			return this;
		}

		@Override
		public DmgSet setDmg(float dmg)
		{
			missile.setDmg(dmg);
			return this;
		}

		@Override
		public SpeedSet setSpeed(float speed)
		{
			missile.setSpeed(speed);
			return this;
		}

		@Override
		public AccelerationSet setAcceleration(float acceleration)
		{
			missile.setAcceleration(acceleration);
			return this;
		}

		@Override
		public MovementSet setMovement(Vector2 movement)
		{
			missile.setMovement(movement);
			return this;
		}

		@Override
		public PositionSet setPosition(Vector2 position)
		{
			if (position != null)
				missile.setPosition(position.x, position.y);

			return this;
		}

		@Override
		public Missile build()
		{
			return missile;
		}
	}

	public interface Builder
	{
		public TextureSet setTexture(Texture texture);
	}

	public interface TextureSet
	{
		public DmgSet setDmg(float dmg);
	}

	public interface DmgSet
	{
		public SpeedSet setSpeed(float speed);
	}

	public interface SpeedSet
	{
		public AccelerationSet setAcceleration(float acceleration);
	}

	public interface AccelerationSet
	{
		public MovementSet setMovement(Vector2 movement);
	}

	public interface MovementSet
	{
		public PositionSet setPosition(Vector2 position);
	}

	public interface PositionSet
	{
		public Missile build();
	}
}
