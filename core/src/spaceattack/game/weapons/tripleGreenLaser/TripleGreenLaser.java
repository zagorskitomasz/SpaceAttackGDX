package spaceattack.game.weapons.tripleGreenLaser;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.greenLaser.GreenLaser;
import spaceattack.game.weapons.missiles.Missile;

public class TripleGreenLaser extends GreenLaser {

    private final IVectorFactory vectors;

    TripleGreenLaser(final int armory, final int mastery, final int speedFactor) {

        super();
        vectors = Factories.getVectorFactory();

        dmg = Consts.Weapons.TRIPLE_GREEN_DMG_PER_ATTR * armory * (1 + Consts.Weapons.DAMAGE_MASTERY_FACTOR * mastery);
        speed = Consts.Weapons.TRIPLE_GREEN_SPEED_PER_ATTR * (10 + armory)
                * (1 + Consts.Weapons.SPEED_FACTOR * speedFactor);
        energyCost = Consts.Weapons.TRIPLE_GREEN_COST_PER_ATTR * armory;
    }

    @Override
    public boolean use() {

        if (!frameController.check()) {
            return false;
        }

        if (!controller.takeEnergy(energyCost)) {
            return false;
        }

        IVector centralPosition = controller.getSecondaryWeaponUsePlacement();

        launchMissiles(centralPosition);
        return true;
    }

    private void launchMissiles(final IVector centralPosition) {

        Missile left = buildMissile();
        left.setSound(null);
        left.setPosition(
                vectors.create(centralPosition.getX() - Sizes.MULTI_MISSILES_X_DISTANCE, centralPosition.getY()));

        Missile right = buildMissile();
        right.setSound(null);
        right.setPosition(
                vectors.create(centralPosition.getX() + Sizes.MULTI_MISSILES_X_DISTANCE, centralPosition.getY()));

        float centralShotMovement = controller.isPlayer() ? -1 : 1;

        Missile central = buildMissile();
        central.setPosition(
                vectors.create(centralPosition.getX(),
                        centralPosition.getY() - Sizes.MULTI_MISSILES_Y_DISTANCE * centralShotMovement));

        launcher.launch(left);
        launcher.launch(right);
        launcher.launch(central);
    }

    @Override
    protected Missile buildMissile() {

        Missile missile = new Missile();

        missile.setActor(Factories.getActorFactory().create(missile));
        missile.setTexture(Textures.TURBO_LASER.getTexture());
        missile.setDmg(dmg);
        missile.setSpeed(speed);
        missile.setAcceleration(0);
        missile.setMovement(controller.getWeaponMovement());
        missile.setPosition(controller.getSecondaryWeaponUsePlacement());
        missile.setSound(Sounds.TURBO_LASER);
        missile.setPlayersAttack(controller.isPlayer());

        return missile;
    }
}
