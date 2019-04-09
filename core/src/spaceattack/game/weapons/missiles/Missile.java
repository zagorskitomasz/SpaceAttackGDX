package spaceattack.game.weapons.missiles;

import java.util.List;

import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.Killable;
import spaceattack.game.actors.interfaces.Vulnerable;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.player.PlayerShip;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;

public class Missile extends AbstractMissile implements Killable
{
	private Iterable<IGameActor> actors;

	private ITexture texture;
	private float dmg;
	private float speed;
	private float acceleration;
	private IVector movement;

	@Override
	public void setActors(List<IGameActor> iterable)
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
			if (actor instanceof Vulnerable && differentFraction(actor))
				collision((Vulnerable) actor);
		}
	}

	private boolean differentFraction(IGameActor actor) 
	{
		return (isPlayersAttack && actor instanceof IEnemyShip) || (!isPlayersAttack && actor instanceof PlayerShip);
	}

	protected void move()
	{
		getActor().setX(getActor().getX() + movement.getX() * speed);
		getActor().setY(getActor().getY() + movement.getY() * speed);

		speed += acceleration;
	}

	private void collision(Vulnerable vulnerable)
	{
		if (!isToKill() && checkCollision(vulnerable))
		{
			vulnerable.takeDmg(dmg);
			setToKill();
		}
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

	@Override
	public void launched()
	{
		playSound();
	}
}
