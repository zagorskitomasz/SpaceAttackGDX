package spaceattack.game.ai.movers;

import spaceattack.consts.Consts;
import spaceattack.game.ai.EnemyBase.Direction;
import spaceattack.game.utils.vector.IVector;

public class SideFrontChaser extends AbstractMover {

    private float destinationMovement = 0;

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

        IVector destination = vectors.create(playerShip.getX() + destinationMovement,
                playerShip.getY() + Consts.AI.FRONT_CHASER_DISTANCE);

        if (isInRadius(destination)) {
            return;
        }

        owner.setDestination(destination);
    }

    @Override
    public void setSiderDirection(final Direction direction) {

        destinationMovement = Consts.AI.SIDE_CHASER_MOVEMENT * direction.getFactor();
    }
}
