package spaceattack.game.ai.movers;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ships.enemy.IEnemyShip;

public abstract class AbstractMover implements MoverAI
{
	protected RadarVisible playerShip;
	protected IEnemyShip owner;

	@Override
	public void setPlayerShip(RadarVisible playerShip)
	{
		this.playerShip = playerShip;
	}

	@Override
	public void setOwner(IEnemyShip owner)
	{
		this.owner = owner;
	}
}
