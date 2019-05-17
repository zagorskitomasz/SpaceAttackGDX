package spaceattack.game.ai.movers;

import spaceattack.consts.Consts;
import spaceattack.game.utils.vector.IVector;

public class FrontChaser extends AbstractMover {

    @Override
    public MoverType getType() {

        return MoverType.FRONT_CHASER;
    }

    @Override
    public void updateDirection() {

        if (playerShip == null || owner == null) {
            return;
        }

        if (owner.isMoving()) {
            return;
        }

        IVector destination = vectors.create(playerShip.getX(), playerShip.getY() + Consts.AI.FRONT_CHASER_DISTANCE);

        if (isInRadius(destination)) {
            return;
        }

        owner.setDestination(destination);
    }
}
