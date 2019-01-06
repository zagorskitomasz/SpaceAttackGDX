package com.zagorskidev.spaceattack.weapons.missiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zagorskidev.spaceattack.sound.Sounds;

import spaceattack.game.actors.DrawableActor;
import spaceattack.game.utils.NumbersUtils;

public class Missile extends DrawableActor implements Killable
{
	private Array<Actor> actors;

	private Texture texture;
	private float dmg;
	private float speed;
	private float acceleration;
	private Vector2 movement;
	private Sounds sound;

	private boolean isToKill;

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
				collision((Vulnerable) actor);
		}
	}

	private void move()
	{
		setX(getX() + movement.x * speed);
		setY(getY() + movement.y * speed);

		speed += acceleration;
	}

	private void collision(Vulnerable vulnerable)
	{
		if (checkCollision(vulnerable))
		{
			vulnerable.takeDmg(dmg);
			setToKill();
		}
	}

	private boolean checkCollision(Vulnerable vulnerable)
	{
		Vector2 missileCenter = new Vector2(getX(), getY());
		Vector2 vulnerableCenter = vulnerable.getPosition();

		return NumbersUtils.distance(missileCenter, vulnerableCenter) <= vulnerable.getRadius() + getRadius();
	}

	private float getRadius()
	{
		return (getHeight() + getWidth()) * 0.25f;
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

	public void setSound(Sounds sound)
	{
		this.sound = sound;
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

	@Override
	public void setToKill()
	{
		isToKill = true;
	}

	@Override
	public boolean isToKill()
	{
		return isToKill;
	}

	public Sounds getSound()
	{
		return sound;
	}
}
