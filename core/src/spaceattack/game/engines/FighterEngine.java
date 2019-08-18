package spaceattack.game.engines;

import spaceattack.consts.Sizes;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.IShip.Turn;
import spaceattack.game.utils.IUtils;

public class FighterEngine extends DestinationShipEngine {

    public FighterEngine(final IShip ship, final IUtils utils, final int engineAttr) {

        super(ship, utils, engineAttr);

        baseSpeed = 0.2f * engineAttr * Sizes.RADIUS_FACTOR;
        acceleration = 0.1f * engineAttr * Sizes.RADIUS_FACTOR;
        braking = 0.1f * engineAttr * Sizes.RADIUS_FACTOR;
        agility = 0.1f * engineAttr * Sizes.RADIUS_FACTOR;
    }

    @Override
    public Turn fly() {

        if (!fixedDestination && destination != null) {
            destination.setY(ship.getY());
        }

        Turn result = super.fly();
        if (fixedDestination) {
            ship.setY(ship.getY() > destination.getY() ? ship.getY() - baseSpeed : ship.getY() + baseSpeed);
        }
        else {
            ship.setY(ship.getY() - baseSpeed);
        }
        return result;
    }

    @Override
    public boolean isDestinationReached() {

        return destination == null || ship.getX() == destination.getX();
    }
}
