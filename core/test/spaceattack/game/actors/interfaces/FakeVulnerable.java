package spaceattack.game.actors.interfaces;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;
import spaceattack.game.utils.vector.IVector;

public class FakeVulnerable implements Vulnerable,IGameActor
{
	@Override
	public void setToKill()
	{
		// do nothing
	}

	@Override
	public boolean isToKill()
	{
		// do nothing
		return false;
	}

	@Override
	public IActor getActor()
	{
		// do nothing
		return null;
	}

	@Override
	public void setActor(IActor actor)
	{
		// do nothing
	}

	@Override
	public void act(float delta)
	{
		// do nothing
	}

	@Override
	public void draw(IBatch batch,float alpha)
	{
		// do nothing
	}

	@Override
	public void takeDmg(float dmg)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public float getRadius()
	{
		// do nothing
		return 0;
	}

	@Override
	public IVector getPosition()
	{
		// do nothing
		return null;
	}
}
