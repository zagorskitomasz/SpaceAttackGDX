package spaceattack.game.weapons;

public interface IWeapon {

    void setLevel(int level);

    boolean use();

    float getWeaponsMovementFactor();

    float getEnergyCost();

    float getCollisionRadius();

    default boolean isContinuousFire() {

        return false;
    }

    void setNoEnergyCost();

    void setInterval(float checksPerSecond);
}
