package spaceattack.game.weapons.targetedRedLaser;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.laser.Laser;
import spaceattack.game.weapons.missiles.Missile;

public class TargetedRedLaser extends Laser {

    private Textures texture;

    private IVector movement;
    private float distanceFromShip = 1f;
    private float gunMovX;
    private float gunMovY;

    TargetedRedLaser(final int armory) {

        dmg = Consts.Weapons.TRED_LASER_DMG_PER_ATTR * armory;
        speed = Consts.Weapons.TRED_LASER_SPEED_PER_ATTR * armory;
        energyCost = Consts.Weapons.TRED_LASER_COST_PER_ATTR * armory;
    }

    @Override
    public void setUtils(final IUtils utils) {

        super.setUtils(utils);
        frameController.reset(Consts.Weapons.TARGETED_LASER_ATTACKS_PER_SECOND);
    }

    @Override
    protected Missile buildMissile() {

        Missile missile = new Missile();

        prepareDirection();

        missile.setActor(Factories.getActorFactory().create(missile));
        missile.setTexture(texture.getTexture());
        missile.setDmg(dmg);
        missile.setSpeed(speed);
        missile.setAcceleration(0);
        missile.setMovement(movement);
        missile.setPosition(calculateTargetedShotPosition(distanceFromShip, gunMovX, gunMovY));
        missile.setRadius(Consts.Weapons.LASER_RADIUS);
        missile.setSound(Sounds.RED_LASER);
        missile.setPlayersAttack(controller.isPlayer());

        return missile;
    }

    private void prepareDirection() {

        movement = controller.getTargetedWeaponMovement();
        setTexture();
    }

    private void setTexture() {

        float xDirection = movement.getX();
        float yDirection = movement.getY();

        if (xDirection < 0 && yDirection < -0 || xDirection > 0 && yDirection > 0) {
            texture = Textures.RED_LASER_S2;
        }
        else
            if (xDirection < 0 && yDirection > 0 || xDirection > 0 && yDirection < 0) {
                texture = Textures.RED_LASER_S1;
            }
            else
                if (xDirection == 0f) {
                    texture = Textures.RED_LASER_NS;
                }
                else {
                    texture = Textures.RED_LASER_WE;
                }
    }

    Textures getTexture() {

        return texture;
    }

    @Override
    public float getWeaponsMovementFactor() {

        return 0;
    }

    public void setDistanceFromShip(final float dist) {

        distanceFromShip = dist;
    }

    public void setGunMovement(final float gunMovX, final float gunMovY) {

        this.gunMovX = gunMovX;
        this.gunMovY = gunMovY;
    }
}
