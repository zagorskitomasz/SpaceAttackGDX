package spaceattack.game.engines;

import spaceattack.game.ships.IShip;
import spaceattack.game.utils.IUtils;

public class FastDestinationShipEngine extends DestinationShipEngine {

    FastDestinationShipEngine(IShip ship, IUtils utils) {

        super(ship, utils);
    }

    @Override
    public void setLevel(int level) {

        super.setLevel(level * 2);
    }
}
