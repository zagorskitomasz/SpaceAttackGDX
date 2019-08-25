package spaceattack.game.weapons;

import spaceattack.game.utils.vector.IVector;

public interface IWeapon {

    boolean use();

    float getWeaponsMovementFactor();

    float getEnergyCost();

    float getCollisionRadius();

    default boolean isContinuousFire() {

        return false;
    }

    void setNoEnergyCost();

    void setInterval(float checksPerSecond);

    IVector calculateTargetedShotPosition(float distanceFromShip, float gunMovX, float gunMovY);
}
