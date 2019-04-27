package spaceattack.game.weapons.miner;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.AbstractWeapon;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;
import spaceattack.game.weapons.missiles.Missile;

public class FlyingMiner extends AbstractWeapon {

    private int level;

    @Override
    public float getWeaponsMovementFactor() {

        return 0.7f;
    }

    @Override
    public float getCollisionRadius() {

        return Consts.Weapons.MINE_RADIUS;
    }

    @Override
    public void setLevel(final int level) {

        this.level = level;
        energyCost = Consts.Weapons.MINE_BASE_COST + (level - 1) * Consts.Weapons.MINE_COST_PER_LEVEL;
    }

    @Override
    public void setUtils(final IUtils utils) {

        super.setUtils(utils);
        frameController.reset(Consts.Weapons.MINE_ATTACKS_PER_SECOND);
    }

    @Override
    protected Missile buildMissile() {

        FlyingMine missile = new FlyingMine(Consts.Weapons.MINE_DELAY);

        missile.setActor(Factories.getActorFactory().create(missile));
        missile.setTexture(Textures.MINE.getTexture());
        missile.setDmg(0);
        missile.setSpeed(Consts.Weapons.FLYING_MINE_SPEED * level / 6);
        missile.setAcceleration(0);
        missile.setMovement(Factories.getVectorFactory().create(0, 0));
        missile.setPosition(calculateTargetedShotPosition(0.3f));
        missile.setTargetCoordsSupplier(controller::getTargetCoords);
        missile.setRadius(Consts.Weapons.MINE_RADIUS);
        missile.setSound(Sounds.MINE);
        missile.setExplosion(ExplosionsBuilder.INSTANCE.createMineExplosion(level));
        missile.setMissilesLauncher(launcher);
        missile.setPlayersAttack(controller.isPlayer());

        return missile;
    }

    float getDmg() {

        return dmg;
    }
}
