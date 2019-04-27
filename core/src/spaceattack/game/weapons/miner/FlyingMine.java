package spaceattack.game.weapons.miner;

import java.util.function.Supplier;

import spaceattack.game.factories.Factories;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class FlyingMine extends Mine {

    private final IVectorFactory vectors;
    private Supplier<IVector> coordsSupplier;

    FlyingMine(final long explodeAfterMillis) {

        super(explodeAfterMillis);
        vectors = Factories.getVectorFactory();
    }

    @Override
    protected void move() {

        if (coordsSupplier == null) {

            return;
        }
        IVector targetCoords = coordsSupplier.get();

        if (targetCoords == null) {

            return;
        }
        IVector direction = vectors.create(targetCoords.getX() - getX(), targetCoords.getY() - getY());
        IVector normalized = direction.normalize();

        float xMovement = normalized.getX() * getSpeed();
        float yMovement = normalized.getY() * getSpeed();

        setX(getX() + xMovement);
        setY(getY() + yMovement);
    }

    public void setTargetCoordsSupplier(final Supplier<IVector> coordsSupplier) {

        this.coordsSupplier = coordsSupplier;
    }
}
