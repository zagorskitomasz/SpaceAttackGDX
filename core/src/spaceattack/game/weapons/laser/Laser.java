package spaceattack.game.weapons.laser;

import spaceattack.consts.Consts;
import spaceattack.game.weapons.AbstractWeapon;

public abstract class Laser extends AbstractWeapon {

    @Override
    public float getWeaponsMovementFactor() {

        return 0.7f;
    }

    @Override
    public float getCollisionRadius() {

        return Consts.Weapons.LASER_RADIUS;
    }
}
