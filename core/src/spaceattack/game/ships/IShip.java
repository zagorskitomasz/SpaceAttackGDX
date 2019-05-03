package spaceattack.game.ships;

import java.util.Set;
import java.util.function.BooleanSupplier;

import spaceattack.game.GameProgress;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.Explosive;
import spaceattack.game.actors.interfaces.Ignitable;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.engines.IEngine;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.notifiers.IObserver;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.IWeapon;

public interface IShip extends IObserver<GameProgress>, Ignitable, IGameActor, RadarVisible, Explosive {

    public enum Turn {
        FRONT, LEFT, RIGHT;
    }

    void setDestination(IVector iVector);

    @Override
    float getX();

    @Override
    float getY();

    void setX(float x);

    void setY(float y);

    void setShipEngine(IEngine engine);

    void addWeapon(IWeapon weapon);

    Set<IWeapon> getWeapons();

    void setLevel(int level);

    float getHeight();

    float getWidth();

    boolean takeEnergy(float energyCost);

    void setHpPool(IPool pool);

    IPool getHpPool();

    void setEnergyPool(IPool pool);

    IPool getEnergyPool();

    void setTexture(ITexture texture);

    boolean exploded();

    void setTemporalImmortalityChecker(BooleanSupplier checker);
}
