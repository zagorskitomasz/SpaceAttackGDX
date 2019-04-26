package spaceattack.game.ai.movers;

import spaceattack.game.utils.vector.IVector;

public class SlowDowner extends AbstractMover {

    @Override
    public MoverType getType() {

        return MoverType.SLOW_DOWNER;
    }

    @Override
    public void updateDirection() {

        if (playerShip == null || owner == null)
            return;

        if (owner.isMoving())
            return;

        IVector destination = vectors.create(owner.getX(), -100);
        owner.setDestination(destination);
    }
}
