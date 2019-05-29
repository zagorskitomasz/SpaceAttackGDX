package spaceattack.game.ai.movers;

import spaceattack.consts.Sizes;
import spaceattack.game.utils.vector.IVector;

public class CentralStation extends AbstractMover {

    @Override
    public MoverType getType() {

        return MoverType.SLOW_DOWNER;
    }

    @Override
    public void updateDirection() {

        if (playerShip == null || owner == null) {
            return;
        }

        if (owner.isMoving()) {
            return;
        }

        IVector destination = vectors.create(Sizes.GAME_WIDTH * 0.5f, Sizes.GAME_HEIGHT - 200 * Sizes.Y_FACTOR);
        owner.setDestination(destination);
    }
}
