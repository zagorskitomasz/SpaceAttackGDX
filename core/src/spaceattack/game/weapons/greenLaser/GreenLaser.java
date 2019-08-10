package spaceattack.game.weapons.greenLaser;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.weapons.laser.Laser;
import spaceattack.game.weapons.missiles.Missile;

public class GreenLaser extends Laser {

    protected GreenLaser(final int armory) {

        dmg = Consts.Weapons.GREEN_LASER_DMG_PER_ATTR * armory;
        speed = Consts.Weapons.GREEN_LASER_SPEED_PER_ATTR * armory;
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
