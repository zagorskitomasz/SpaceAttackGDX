package spaceattack.game.weapons.rocketMissile;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.AbstractWeapon;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.missiles.ExplosiveMissile;
import spaceattack.game.weapons.missiles.Missile;

public class RocketMissile extends AbstractWeapon {

    protected RocketMissile(final int armory) {

        dmg = Consts.Weapons.ROCKET_DMG_PER_ATTR * armory;
        speed = Consts.Weapons.ROCKET_SPEED_PER_ATTR * armory;
        energyCost = Consts.Weapons.ROCKET_COST_PER_ATTR * armory;
    }

    @Override
    public float getWeaponsMovementFactor() {

        return 0.7f;
    }

    @Override
    public float getCollisionRadius() {

        return Consts.Weapons.ROCKET_RADIUS;
    }

    @Override
    public void setUtils(final IUtils utils) {

        super.setUtils(utils);
        frameController.reset(Consts.Weapons.ROCKET_ATTACKS_PER_SECOND);
    }

    @Override
    protected Missile buildMissile() {

        ExplosiveMissile missile = new ExplosiveMissile();

        missile.setActor(Factories.getActorFactory().create(missile));
        missile.setTexture(controller.isPlayer() ? Textures.ROCKET_MISSILE_P.getTexture()
                : Textures.ROCKET_MISSILE_E.getTexture());
        missile.setDmg(0);
        missile.setSpeed(2 * Sizes.Y_FACTOR);
        missile.setAcceleration(0.2f * Sizes.Y_FACTOR);
        missile.setMovement(controller.getWeaponMovement());
        missile.setPosition(controller.getSecondaryWeaponUsePlacement());
        missile.setRadius(Consts.Weapons.ROCKET_RADIUS);
        missile.setSound(Sounds.ROCKET_MISSILE);
        missile.setExplosion(ExplosionsBuilder.INSTANCE.createMissileExplosion(dmg * controller.getDamageFactor()));
        missile.setMissilesLauncher(launcher);
        missile.setPlayersAttack(controller.isPlayer());

        return missile;
    }

    float getDmg() {

        return dmg * controller.getDamageFactor();
    }
}
