package spaceattack.game.engines;

import spaceattack.game.ships.IShip;
import spaceattack.game.utils.IUtils;

public class FastDestinationShipEngine extends DestinationShipEngine {

    FastDestinationShipEngine(final IShip ship, final IUtils utils, final int engineAttr) {

        super(ship, utils, engineAttr * 2);
    }
}
