package spaceattack.game.engines;

import spaceattack.game.ships.IShip.Turn;
import spaceattack.game.utils.vector.IVector;

public interface IEngine
{
	public void setDestination(IVector destination);

	public Turn fly();

	public void setLevel(int level);

	public void setBaseSpeed(float baseSpeed);

	public void setAcceleration(float acceleration);

	public void setBraking(float braking);

	public void setAgility(float agility);

	public boolean isDestinationReached();
}
