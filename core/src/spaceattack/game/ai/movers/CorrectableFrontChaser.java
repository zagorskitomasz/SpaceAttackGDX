package spaceattack.game.ai.movers;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.vector.IVector;

public class CorrectableFrontChaser extends AbstractMover {

    private final FrameController directionUpdater;

    public CorrectableFrontChaser() {

        directionUpdater = new FrameController(Factories.getUtilsFactory().create(),
                Consts.AI.JUMPER_CORRECTIONS_PER_SEC);
    }

    @Override
    public MoverType getType() {

        return MoverType.CORRECTABLE_FRONT_CHASER;
    }

    @Override
    public void updateDirection() {

        if (playerShip == null || owner == null) {
            return;
        }

        if (owner.isMoving() && !directionUpdater.check()) {
            return;
        }

        IVector destination = vectors.create(playerShip.getX(),
                playerShip.getY() + Consts.AI.FRONT_CHASER_DISTANCE * 1.5f);

        if (isInRadius(destination)) {
            return;
        }

        owner.setDestination(destination);
    }
}
