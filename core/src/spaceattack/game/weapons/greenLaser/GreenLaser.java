package spaceattack.game.weapons.greenLaser;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.weapons.laser.Laser;
import spaceattack.game.weapons.missiles.Missile;

public class GreenLaser extends Laser {

    protected GreenLaser() {

        // do nothing
    }

    @Override
    public void setLevel(int level) {

        dmg = Consts.Weapons.GREEN_LASER_BASE_DMG + (level - 1) * Consts.Weapons.GREEN_LASER_DMG_PER_LEVEL;
        speed = Consts.Weapons.GREEN_LASER_BASE_SPEED + (level - 1) * Consts.Weapons.GREEN_LASER_SPEED_PER_LEVEL;
        energyCost = Consts.Weapons.GREEN_LASER_BASE_COST + (level - 1) * Consts.Weapons.GREEN_LASER_COST_PER_LEVEL;
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
