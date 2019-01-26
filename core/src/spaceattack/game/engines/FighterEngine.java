package spaceattack.game.engines;

import spaceattack.game.ships.IShip;
import spaceattack.game.ships.IShip.Turn;
import spaceattack.game.utils.IUtils;

public class FighterEngine extends ShipEngine
{
	public FighterEngine(IShip ship,IUtils utils)
	{
		super(ship, utils);
	}

	@Override
	public Turn fly()
	{
		if (destination != null)
			destination.setY(ship.getY());

		Turn result = super.fly();
		ship.setY(ship.getY() - baseSpeed);
		return result;
	}

	@Override
	public boolean isDestinationReached()
	{
		return destination == null || ship.getX() == destination.getX();
	}
}
