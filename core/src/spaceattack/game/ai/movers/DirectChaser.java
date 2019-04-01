package spaceattack.game.ai.movers;

import spaceattack.game.utils.vector.IVector;

public class DirectChaser extends AbstractMover
{
	@Override
	public MoverType getType()
	{
		return MoverType.DIRECT_CHASER;
	}

	@Override
	public void updateDirection()
	{
		if (playerShip == null || owner == null)
			return;

		if (owner.isMoving())
			return;

		IVector destination = vectors.create(playerShip.getX(), playerShip.getY());

		if (isInRadius(destination))
			return;

		owner.setDestination(destination);
	}
}
