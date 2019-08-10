package spaceattack.game.weapons.multiRedLaser;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.missiles.Missile;

public class DistractedRedLaser extends MultiShotRedLaser {

    DistractedRedLaser(final int armory) {

        super(armory);

        dmg = Consts.Weapons.DISTRACTED_RED_DMG_PER_ATTR * armory;
        speed = Consts.Weapons.DISTRACTED_RED_SPEED_PER_ATTR * armory;
        energyCost = Consts.Weapons.DISTRACTED_RED_COST_PER_ATTR * armory;
    }

    @Override
    protected void launchMissiles(final IVector centralPosition) {

        IVector leftMovement = vectors.create(-1, controller.getWeaponMovement().getY());
        Missile left = buildMissile();
        left.setSound(null);
        left.setTexture(Textures.RED_LASER_S2.getTexture());
        left.setMovement(leftMovement);
        left.setPosition(
                vectors.create(centralPosition.getX() - Sizes.MULTI_MISSILES_X_DISTANCE, centralPosition.getY()));

        IVector rightMovement = vectors.create(1, controller.getWeaponMovement().getY());
        Missile right = buildMissile();
        right.setSound(null);
        right.setTexture(Textures.RED_LASER_S1.getTexture());
        right.setMovement(rightMovement);
        right.setPosition(
                vectors.create(centralPosition.getX() + Sizes.MULTI_MISSILES_X_DISTANCE, centralPosition.getY()));

        Missile straight = buildMissile();

        launcher.launch(left);
        launcher.launch(right);
        launcher.launch(straight);
    }
}
