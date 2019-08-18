package spaceattack.game.engines;

import spaceattack.game.ships.IShip.Turn;
import spaceattack.game.utils.vector.IVector;

public interface IEngine {

    void setDestination(IVector destination);

    void forceDestination(IVector destination);

    Turn fly();

    boolean isDestinationReached();
}
