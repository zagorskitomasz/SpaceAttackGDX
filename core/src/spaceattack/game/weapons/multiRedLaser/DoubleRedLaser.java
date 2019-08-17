package spaceattack.game.weapons.multiRedLaser;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.missiles.Missile;

public class DoubleRedLaser extends MultiShotRedLaser {

    DoubleRedLaser(final int armory, final int mastery) {

        super(armory);

        dmg = Consts.Weapons.DOUBLE_RED_DMG_PER_ATTR * armory * (1 + Consts.Weapons.DAMAGE_MASTERY_FACTOR * mastery);
        speed = Consts.Weapons.DOUBLE_RED_SPEED_PER_ATTR * armory;
        energyCost = Consts.Weapons.DOUBLE_RED_COST_PER_ATTR * armory;
    }

    @Override
    protected void launchMissiles(final IVector centralPosition) {

        Missile left = buildMissile();
        left.setSound(null);
        left.setPosition(
                vectors.create(centralPosition.getX() - Sizes.MULTI_MISSILES_X_DISTANCE / 2, centralPosition.getY()));

        Missile right = buildMissile();
        right.setPosition(
                vectors.create(centralPosition.getX() + Sizes.MULTI_MISSILES_X_DISTANCE / 2, centralPosition.getY()));

        launcher.launch(left);
        launcher.launch(right);
    }
}
