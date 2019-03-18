package spaceattack.game.ai.movers;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.utils.vector.IVectorFactory;

public abstract class AbstractMover implements MoverAI
{
	protected RadarVisible playerShip;
	protected IEnemyShip owner;
	protected IVectorFactory vectors;

	public AbstractMover()
	{
		vectors = Factories.getVectorFactory();
	}

	@Override
	public void setPlayerShip(RadarVisible playerShip)
	{
		this.playerShip = playerShip;
	}

	@Override
	public void setOwner(IEnemyShip owner)
	{
		this.owner = owner;
		owner.setDestination(vectors.create(owner.getX(), owner.getY()));
	}
}
