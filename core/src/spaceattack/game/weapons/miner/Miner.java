package spaceattack.game.weapons.miner;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.AbstractWeapon;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.missiles.ExplosiveMissile;
import spaceattack.game.weapons.missiles.Missile;

public class Miner extends AbstractWeapon {

    public Miner(final int armory) {

        dmg = Consts.Weapons.MINER_DMG_PER_ATTR * armory;
        speed = Consts.Weapons.MINER_SPEED_PER_ATTR * armory;
        energyCost = Consts.Weapons.MINER_COST_PER_ATTR * armory;
    }

    @Override
    public float getWeaponsMovementFactor() {

        return 0.7f;
    }

    @Override
    public float getCollisionRadius() {

        return Consts.Weapons.MINE_RADIUS;
    }

    @Override
    public void setUtils(final IUtils utils) {

        super.setUtils(utils);
        frameController.reset(Consts.Weapons.MINE_ATTACKS_PER_SECOND);
    }

    @Override
    protected Missile buildMissile() {

        ExplosiveMissile missile = new Mine(Consts.Weapons.MINE_DELAY);

        missile.setActor(Factories.getActorFactory().create(missile));
        missile.setTexture(Textures.MINE.getTexture());
        missile.setDmg(0);
        missile.setSpeed(0);
        missile.setAcceleration(0);
        missile.setMovement(controller.getWeaponMovement());
        missile.setPosition(controller.getSecondaryWeaponUsePlacement());
        missile.setRadius(Consts.Weapons.MINE_RADIUS);
        missile.setSound(Sounds.MINE);
        missile.setExplosion(ExplosionsBuilder.INSTANCE.createMineExplosion(dmg * controller.getDamageFactor()));
        missile.setMissilesLauncher(launcher);
        missile.setPlayersAttack(controller.isPlayer());

        return missile;
    }

    float getDmg() {

        return dmg * controller.getDamageFactor();
    }
}
