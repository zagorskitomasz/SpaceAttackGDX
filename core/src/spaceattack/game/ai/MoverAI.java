package spaceattack.game.ai;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ships.enemy.IEnemyShip;

public interface MoverAI
{
	public MoverType getType();

	public void setPlayerShip(RadarVisible playerShip);

	public void setOwner(IEnemyShip fighter);
}
