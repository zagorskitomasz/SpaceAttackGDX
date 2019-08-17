package spaceattack.game.weapons.redLaser;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.weapons.laser.Laser;
import spaceattack.game.weapons.missiles.Missile;

public class RedLaser extends Laser {

    protected RedLaser(final int armory, final int mastery, final int speedFactor) {

        dmg = Consts.Weapons.RED_LASER_DMG_PER_ATTR * armory * (1 + Consts.Weapons.DAMAGE_MASTERY_FACTOR * mastery);
        speed = Consts.Weapons.RED_LASER_SPEED_PER_ATTR * (10 + armory)
                * (1 + Consts.Weapons.SPEED_FACTOR * speedFactor);
        energyCost = Consts.Weapons.RED_LASER_COST_PER_ATTR * armory;
    }

    @Override
    protected Missile buildMissile() {

        Missile missile = new Missile();

        missile.setActor(Factories.getActorFactory().create(missile));
        missile.setTexture(Textures.RED_LASER_NS.getTexture());
        missile.setDmg(dmg);
        missile.setSpeed(speed);
        missile.setAcceleration(0);
        missile.setMovement(controller.getWeaponMovement());
        missile.setPosition(controller.getPrimaryWeaponUsePlacement());
        missile.setRadius(Consts.Weapons.LASER_RADIUS);
        missile.setSound(Sounds.RED_LASER);
        missile.setPlayersAttack(controller.isPlayer());

        return missile;
    }
}
