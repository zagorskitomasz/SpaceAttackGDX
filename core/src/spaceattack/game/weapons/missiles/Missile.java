package spaceattack.game.weapons.missiles;

import spaceattack.game.actors.DrawableActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.Killable;
import spaceattack.game.actors.interfaces.Vulnerable;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.NumbersUtils;
import spaceattack.game.utils.vector.IVector;

public class Missile extends DrawableActor implements Killable
{
	private Iterable<IGameActor> actors;

	private ITexture texture;
	private float dmg;
	private float speed;
	private float acceleration;
	private IVector movement;
	private Sounds sound;

	private boolean isToKill;

	public void setActors(Iterable<IGameActor> iterable)
	{
		this.actors = iterable;
	}

	@Override
	public void act(float delta)
	{
		move();

		if (actors == null)
			return;

		for (IGameActor actor : actors)
		{
			if (actor instanceof Vulnerable)
				collision((Vulnerable) actor);
		}
	}

	private void move()
	{
		getActor().setX(getActor().getX() + movement.getX() * speed);
		getActor().setY(getActor().getY() + movement.getY() * speed);

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
		IVector missileCenter = Factories.getVectorFactory().create(getActor().getX(), getActor().getY());
		IVector vulnerableCenter = vulnerable.getPosition();

		return NumbersUtils.distance(missileCenter, vulnerableCenter) <= vulnerable.getRadius() + getRadius();
	}

	private float getRadius()
	{
		return (getActor().getHeight() + getActor().getWidth()) * 0.25f;
	}

	@Override
	protected ITexture getTexture()
	{
		return texture;
	}

	public void setTexture(ITexture texture)
	{
		this.texture = texture;
	}

	public void setDmg(float dmg)
	{
		this.dmg = dmg;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public void setAcceleration(float acceleration)
	{
		this.acceleration = acceleration;
	}

	public void setMovement(IVector movement)
	{
		if (movement != null)
			this.movement = movement.normalize();
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

	public IVector getPosition()
	{
		return Factories.getVectorFactory().create(getActor().getX(), getActor().getY());
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

	public void playSound()
	{
		sound.play();
	}

	public void setPosition(IVector position)
	{
		if (getActor() != null)
		{
			getActor().setX(position.getX());
			getActor().setY(position.getY());
		}
	}
}
