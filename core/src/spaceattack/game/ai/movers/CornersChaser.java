package spaceattack.game.ai.movers;

import spaceattack.consts.Consts;
import spaceattack.game.utils.NumbersUtils;
import spaceattack.game.utils.vector.IVector;

public class CornersChaser extends AbstractMover
{
	@Override
	public MoverType getType()
	{
		return MoverType.FRONT_CHASER;
	}

	@Override
	public void updateDirection()
	{
		if (playerShip == null || owner == null)
			return;

		if (owner.isMoving())
			return;

		int factor = Math.random() < 0.5 ? -1 : 1;
		
		IVector destination = vectors.create(playerShip.getX() + factor * Consts.AI.FRONT_CHASER_DISTANCE * 0.7f, playerShip.getY() + Consts.AI.FRONT_CHASER_DISTANCE * 0.7f);

		if (isInRadius(destination))
			return;

		owner.setDestination(destination);
	}

	private boolean isInRadius(IVector destination)
	{
		return NumbersUtils.distance(owner.getPosition(), destination) < playerShip.getRadius();
	}
}
