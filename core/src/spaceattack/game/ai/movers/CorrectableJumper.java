package spaceattack.game.ai.movers;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.NumbersUtils;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class CorrectableJumper extends AbstractMover {

    private IVectorFactory vectors;
    private FrameController directionUpdater;
    private boolean isCharging;

    public CorrectableJumper() {

        vectors = Factories.getVectorFactory();

        directionUpdater = new FrameController(Factories.getUtilsFactory().create(),
                Consts.AI.JUMPER_CORRECTIONS_PER_SEC);
        isCharging = true;
    }

    @Override
    public MoverType getType() {

        return MoverType.CORRECTABLE_JUMPER;
    }

    @Override
    public void updateDirection() {

        if (owner == null || playerShip == null)
            return;

        if (!isCharging) {
            if (escaped()) {
                charge();
            }
        }
        else {
            if (charged()) {
                notifyObservers();
                escape();
            }
            else
                if (directionUpdater.check()) {
                    correct();
                }
        }
    }

    private boolean escaped() {

        return owner.getY() > Sizes.GAME_HEIGHT + 100;
    }

    private void charge() {

        isCharging = true;
        IVector destination = vectors.create(playerShip.getX(), playerShip.getY());
        owner.setDestination(destination);
    }

    private boolean charged() {

        double distanceToPlayer = NumbersUtils.distance(owner.getPosition(),
                vectors.create(playerShip.getX(), playerShip.getY()));
        return distanceToPlayer < Consts.AI.JUMPER_PLAYER_DISTANCE || owner.getY() < playerShip.getY();
    }

    private void escape() {

        isCharging = false;
        IVector destination = vectors.create((float) (Math.random() * Sizes.GAME_WIDTH), Sizes.GAME_HEIGHT + 150);
        owner.setDestination(destination);
    }

    private void correct() {

        IVector destination = vectors.create(playerShip.getX(), playerShip.getY());
        owner.setDestination(destination);
    }
}
