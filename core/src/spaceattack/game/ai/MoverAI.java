package spaceattack.game.ai;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.notifiers.INotifier;

public interface MoverAI extends INotifier<MoverAI> {

    MoverType getType();

    void setPlayerShip(RadarVisible playerShip);

    void setOwner(IEnemyShip fighter);

    void updateDirection();
}
