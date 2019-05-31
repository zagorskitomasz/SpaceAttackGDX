package spaceattack.game.ai.movers;

import java.util.function.Supplier;

import spaceattack.game.utils.vector.IVector;

public class Follower extends AbstractMover {

    private Supplier<IVector> coordsSupplier;

    @Override
    public MoverType getType() {

        return MoverType.SLOW_DOWNER;
    }

    public void setCoordsSupplier(final Supplier<IVector> coordsSupplier) {

        this.coordsSupplier = coordsSupplier;
    }

    @Override
    public void updateDirection() {

        if (playerShip == null || owner == null) {
            return;
        }

        IVector position = coordsSupplier.get();
        owner.getActor().setPosition(position.getX(), position.getY());
    }
}
