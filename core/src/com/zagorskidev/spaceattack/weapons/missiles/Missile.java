package com.zagorskidev.spaceattack.weapons.missiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zagorskidev.spaceattack.graphics.DrawableActor;

public class Missile extends DrawableActor
{
	private Array<Actor> actors;

	private Texture texture;
	private float dmg;
	private float speed;
	private float acceleration;
	private Vector2 movement;

	Missile()
	{

	}

	public void setActors(Array<Actor> actors)
	{
		this.actors = actors;
	}

	@Override
	public void act(float delta)
	{
		move();

		if (actors == null)
			return;

		for (Actor actor : actors)
		{
			if (actor instanceof Vulnerable)
				collision(actor);
		}
	}

	private void move()
	{
		setX(getX() + movement.x * speed);
		setY(getY() + movement.y * speed);

		speed += acceleration;
	}

	private void collision(Actor actor)
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected Texture getTexture()
	{
		return texture;
	}

	void setTexture(Texture texture)
	{
		this.texture = texture;
	}

	void setDmg(float dmg)
	{
		this.dmg = dmg;
	}

	void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public void setAcceleration(float acceleration)
	{
		this.acceleration = acceleration;
	}

	public void setMovement(Vector2 movement)
	{
		if (movement != null)
			this.movement = movement.nor();
	}

	public float getDmg()
	{
		return dmg;
	}

	public float getSpeed()
	{
		return speed;
	}

	public float getAcceleration()
	{
		return acceleration;
	}

	public Vector2 getPosition()
	{
		return new Vector2(getX(), getY());
	}
}
