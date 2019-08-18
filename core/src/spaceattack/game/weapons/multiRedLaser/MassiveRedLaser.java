package spaceattack.game.weapons.multiRedLaser;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.missiles.Missile;

public class MassiveRedLaser extends MultiShotRedLaser {

    MassiveRedLaser(final int armory, final int mastery, final int speedFactor) {

        super(armory);

        dmg = Consts.Weapons.MASSIVE_RED_DMG_PER_ATTR * armory * (1 + Consts.Weapons.DAMAGE_MASTERY_FACTOR * mastery);
        speed = Consts.Weapons.MASSIVE_RED_SPEED_PER_ATTR * (10 + armory)
                * (1 + Consts.Weapons.SPEED_FACTOR * speedFactor);
        energyCost = Consts.Weapons.MASSIVE_RED_COST_PER_ATTR * armory;
    }

    @Override
    protected void launchMissiles(final IVector centralPosition) {

        IVector leftCrossMovement = vectors.create(-1, controller.getWeaponMovement().getY());
        Missile leftCross = buildMissile();
        leftCross.setSound(null);

        if (controller.getWeaponMovement().getY() > 0) {
            leftCross.setTexture(Textures.RED_LASER_S1.getTexture());
        }
        else {
            leftCross.setTexture(Textures.RED_LASER_S2.getTexture());
        }

        leftCross.setMovement(leftCrossMovement);
        leftCross.setPosition(
                vectors.create(centralPosition.getX() - Sizes.MULTI_MISSILES_X_DISTANCE * 1.2f,
                        centralPosition.getY()));

        IVector rightCrossMovement = vectors.create(1, controller.getWeaponMovement().getY());
        Missile rightCross = buildMissile();
        rightCross.setSound(null);

        if (controller.getWeaponMovement().getY() > 0) {
            rightCross.setTexture(Textures.RED_LASER_S2.getTexture());
        }
        else {
            rightCross.setTexture(Textures.RED_LASER_S1.getTexture());
        }

        rightCross.setMovement(rightCrossMovement);
        rightCross.setPosition(
                vectors.create(centralPosition.getX() + Sizes.MULTI_MISSILES_X_DISTANCE * 1.2f,
                        centralPosition.getY()));

        Missile straight1 = buildMissile();
        straight1.setSound(null);
        straight1.setPosition(
                vectors.create(centralPosition.getX() - Sizes.MULTI_MISSILES_X_DISTANCE * 1.2f,
                        centralPosition.getY()));

        Missile straight2 = buildMissile();
        straight2.setSound(null);
        straight2.setPosition(
                vectors.create(centralPosition.getX() - Sizes.MULTI_MISSILES_X_DISTANCE * 0.8f, centralPosition.getY()
                        + Sizes.MULTI_MISSILES_Y_DISTANCE * controller.getWeaponMovement().getY()));

        Missile straight3 = buildMissile();
        straight3.setSound(null);
        straight3.setPosition(
                vectors.create(centralPosition.getX() + Sizes.MULTI_MISSILES_X_DISTANCE * 0.8f, centralPosition.getY()
                        + Sizes.MULTI_MISSILES_Y_DISTANCE * controller.getWeaponMovement().getY()));

        Missile straight4 = buildMissile();
        straight4.setPosition(
                vectors.create(centralPosition.getX() + Sizes.MULTI_MISSILES_X_DISTANCE * 1.2f,
                        centralPosition.getY()));

        launcher.launch(leftCross);
        launcher.launch(rightCross);
        launcher.launch(straight1);
        launcher.launch(straight2);
        launcher.launch(straight3);
        launcher.launch(straight4);
    }
}
