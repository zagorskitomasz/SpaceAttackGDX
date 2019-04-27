package spaceattack.game.ai;

import java.util.List;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.notifiers.IObserver;

public interface ShooterAI extends IObserver<MoverAI> {

    ShooterType getType();

    void setPlayerShip(RadarVisible playerShip);

    void setOwner(IEnemyShip fighter);

    PossibleAttacks checkShot();

    void setFriends(List<? extends RadarVisible> friends);
}
