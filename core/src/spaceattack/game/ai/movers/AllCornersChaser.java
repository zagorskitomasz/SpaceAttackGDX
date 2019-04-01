package spaceattack.game.ai.movers;

import spaceattack.consts.Consts;
import spaceattack.game.utils.vector.IVector;

public class AllCornersChaser extends AbstractMover
{
	@Override
	public MoverType getType()
	{
		return MoverType.ALL_CORNERS_CHASER;
	}

	@Override
	public void updateDirection()
	{
		if (playerShip == null || owner == null)
			return;

		if (owner.isMoving())
			return;

		int factorX = Math.random() < 0.5 ? -1 : 1;
		int factorY = Math.random() < 0.5 ? -1 : 1;
		
		IVector destination = vectors.create(playerShip.getX() + factorX * Consts.AI.FRONT_CHASER_DISTANCE * 0.7f, playerShip.getY() + factorY * Consts.AI.FRONT_CHASER_DISTANCE * 0.7f);

		if (isInRadius(destination))
			return;

		owner.setDestination(destination);
	}
}
