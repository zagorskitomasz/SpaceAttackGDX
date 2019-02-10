package spaceattack.game.powerup;

import spaceattack.game.actors.DrawableActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;

public abstract class AbstractPowerUp extends DrawableActor implements IPowerUp
{
	private Sounds consumeSound;

	@Override
	public void setActors(Iterable<IGameActor> actors)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void playSound()
	{
		// do nothing
	}

	@Override
	public void launched()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void act(float delta)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected ITexture getTexture()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setTexture(Textures energyPowerUp)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setToKill()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isToKill()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
