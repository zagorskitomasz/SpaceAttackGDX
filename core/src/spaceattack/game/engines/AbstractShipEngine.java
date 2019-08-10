package spaceattack.game.engines;

import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.vector.IVectorFactory;

public abstract class AbstractShipEngine implements IEngine {

    protected IVectorFactory vectorFactory;

    protected IShip ship;

    protected float baseSpeed;
    protected float acceleration;
    protected float braking;
    protected float agility;

    protected float currentSpeed;

    public AbstractShipEngine(final IShip ship) {

        this.ship = ship;
        vectorFactory = Factories.getVectorFactory();
    }
}
