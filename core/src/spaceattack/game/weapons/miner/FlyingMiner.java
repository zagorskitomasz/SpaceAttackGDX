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

    private long mineExplosionDelay = Consts.Weapons.MINE_DELAY;
    private float distanceToShip = 0.3f;

    public FlyingMiner(final int armory) {

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

        FlyingMine missile = new FlyingMine(mineExplosionDelay);

        missile.setActor(Factories.getActorFactory().create(missile));
        missile.setTexture(Textures.MINE.getTexture());
        missile.setDmg(0);
        missile.setSpeed(speed);
        missile.setAcceleration(0);
        missile.setMovement(Factories.getVectorFactory().create(0, 0));
        missile.setPosition(calculateTargetedShotPosition(distanceToShip, 0, 0));
        missile.setTargetCoordsSupplier(controller::getTargetCoords);
        missile.setRadius(Consts.Weapons.MINE_RADIUS);
        missile.setSound(Sounds.MINE);
        missile.setExplosion(ExplosionsBuilder.INSTANCE.createMineExplosion(dmg));
        missile.setMissilesLauncher(launcher);
        missile.setPlayersAttack(controller.isPlayer());

        return missile;
    }

    float getDmg() {

        return dmg;
    }

    public void increaseDelay() {

        mineExplosionDelay = Consts.Weapons.SLOW_MINE_DELAY;
        frameController.reset(Consts.Weapons.SLOW_MINE_ATTACKS_PER_SECOND);
    }

    public void setDistanceToShip(final float distanceToShip) {

        this.distanceToShip = distanceToShip;
    }
}
