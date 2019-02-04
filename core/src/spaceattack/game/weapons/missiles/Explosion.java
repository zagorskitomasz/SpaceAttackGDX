package spaceattack.game.weapons.missiles;

import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.Ignitable;
import spaceattack.game.actors.interfaces.Vulnerable;
import spaceattack.game.animations.IAnimation;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.IUtils;

public class Explosion extends AbstractMissile
{
	private Iterable<IGameActor> actors;
	private IAnimation animation;
	private IUtils utils;
	private float dmg;
	private float fireDmg;
	private long fireDuration;

	private float elapsed;

	public void setUtils(IUtils utils)
	{
		this.utils = utils;
	}

	@Override
	public void setActors(Iterable<IGameActor> actors)
	{
		this.actors = actors;
	}

	@Override
	public void launched()
	{
		if (actors == null)
			return;

		playSound();

		for (IGameActor actor : actors)
		{
			if (actor instanceof Vulnerable)
				collision((Vulnerable) actor);
		}
	}

	private void collision(Vulnerable vulnerable)
	{
		if (checkCollision(vulnerable))
		{
			vulnerable.takeDmg(dmg);
			if (vulnerable instanceof Ignitable)
				((Ignitable) vulnerable).ignite(fireDmg, fireDuration);
		}
	}

	@Override
	protected ITexture getTexture()
	{
		if (animation == null)
			return null;

		elapsed += utils.getDeltaTime();
		return animation.getKeyFrame(elapsed);
	}

	public void setAnimation(IAnimation animation)
	{
		this.animation = animation;
	}

	public void setDmg(float dmg)
	{
		this.dmg = dmg;
	}

	public void setFireDmg(float fireDmg)
	{
		this.fireDmg = fireDmg;
	}

	public void setFireDuration(long fireDuration)
	{
		this.fireDuration = fireDuration;
	}

	public float getDmg()
	{
		return dmg;
	}

	public float getFireDmg()
	{
		return fireDmg;
	}

	public float getFireDuration()
	{
		return fireDuration;
	}

	@Override
	public void act(float delta)
	{
		if (animation != null && animation.isCompleted())
			setToKill();
	}
}
