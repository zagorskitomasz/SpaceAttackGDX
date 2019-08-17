package spaceattack.game.weapons.greenLaser;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.weapons.laser.Laser;
import spaceattack.game.weapons.missiles.Missile;

public class GreenLaser extends Laser {

    protected GreenLaser(final int armory, final int mastery, final int speedFactor) {

        dmg = Consts.Weapons.GREEN_LASER_DMG_PER_ATTR * armory * (1 + Consts.Weapons.DAMAGE_MASTERY_FACTOR * mastery);
        speed = Consts.Weapons.GREEN_LASER_SPEED_PER_ATTR * (10 + armory)
                * (1 + Consts.Weapons.SPEED_FACTOR * speedFactor);
        energyCost = Consts.Weapons.GREEN_LASER_COST_PER_ATTR * armory;
    }

    protected GreenLaser() {

        // do nothing
    }

    @Override
    protected Missile buildMissile() {

        Missile missile = new Missile();

        missile.setActor(Factories.getActorFactory().create(missile));
        missile.setTexture(Textures.TURBO_LASER.getTexture());
        missile.setDmg(dmg * controller.getDamageFactor());
        missile.setSpeed(speed);
        missile.setAcceleration(0);
        missile.setMovement(controller.getWeaponMovement());
        missile.setPosition(controller.getSecondaryWeaponUsePlacement());
        missile.setSound(Sounds.TURBO_LASER);
        missile.setPlayersAttack(controller.isPlayer());

        return missile;
    }
}
