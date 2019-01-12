package spaceattack.game.engines;

import com.zagorskidev.spaceattack.ships.IShip.Turn;

import spaceattack.game.utils.vector.IVector;

public interface IEngine
{
	public void setDestination(IVector destination);

	public Turn fly();

	public void setLevel(int level);
}
