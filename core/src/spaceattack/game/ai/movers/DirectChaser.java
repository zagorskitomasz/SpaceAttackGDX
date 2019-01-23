package spaceattack.game.ai.movers;

import spaceattack.game.utils.NumbersUtils;
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
		if (owner.isMoving())
			return;

		IVector destination = vectors.create(playerShip.getX(), playerShip.getY());

		if (isInRadius(destination))
			return;

		owner.setDestination(destination);
	}

	private boolean isInRadius(IVector destination)
	{
		return NumbersUtils.distance(owner.getPosition(), destination) < playerShip.getRadius();
	}
}
