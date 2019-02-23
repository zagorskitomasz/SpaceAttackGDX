package spaceattack.game.weapons.missiles;

import spaceattack.consts.Sizes;
import spaceattack.game.actors.DrawableActor;
import spaceattack.game.actors.interfaces.Killable;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.actors.interfaces.Vulnerable;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.NumbersUtils;
import spaceattack.game.utils.vector.IVector;

public abstract class AbstractMissile extends DrawableActor implements Launchable,Killable
{
	private boolean isToKill;
	private Sounds sound;
	private float radius;

	protected boolean checkCollision(Vulnerable vulnerable)
	{
		IVector missileCenter = Factories.getVectorFactory().create(getActor().getX(), getActor().getY());
		IVector vulnerableCenter = vulnerable.getPosition();

		return NumbersUtils.distance(missileCenter, vulnerableCenter) <= vulnerable.getRadius() + getRadius();
	}

	public float getRadius()
	{
		return radius * Sizes.RADIUS_FACTOR;
	}

	public void setRadius(float radius)
	{
		this.radius = radius;
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

	public void setSound(Sounds sound)
	{
		this.sound = sound;
	}

	@Override
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
