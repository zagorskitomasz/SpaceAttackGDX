package spaceattack.game.ai.movers;

import spaceattack.game.utils.vector.IVector;

public class RightSideChaser extends SideChaser
{
	@Override
	public MoverType getType()
	{
		return MoverType.RIGHT_SIDE_CHASER;
	}

	@Override
	public void updateDirection()
	{
		if (playerShip == null || owner == null)
			return;

		if (owner.isMoving())
			return;

		IVector destination = vectors.create(playerShip.getX() + margin, playerShip.getY());

		if (isInRadius(destination))
			return;

		owner.setDestination(destination);
	}
}
