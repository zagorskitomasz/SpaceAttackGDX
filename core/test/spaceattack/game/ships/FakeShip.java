package spaceattack.game.ships;

import java.util.Set;
import java.util.function.BooleanSupplier;

import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.batch.IBatch;
import spaceattack.game.engines.IEngine;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.rpg.Attributes;
import spaceattack.game.ships.enemy.EnemyBar;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.Freezer;

public class FakeShip implements IShip, IEnemyShip, IBoss {

    private float x;
    private float y;
    private float radius;
    private IPool hpPool;
    private boolean isToKill = false;

    @Override
    public void takeDmg(final float dmg) {

        if (hpPool != null) {
            if (!hpPool.take(dmg)) {
                setToKill();
            }
        }
    }

    @Override
    public float getRadius() {

        return radius;
    }

    @Override
    public IVector getPosition() {

        return ExtVectorFactory.INSTANCE.create(x, y);
    }

    @Override
    public void setToKill() {

        isToKill = true;
    }

    @Override
    public boolean isToKill() {

        return isToKill;
    }

    @Override
    public IActor getActor() {

        return null;
    }

    @Override
    public void setActor(final IActor actor) {

        // do nothing
    }

    @Override
    public void act(final float delta) {

        // do nothing
    }

    @Override
    public void draw(final IBatch batch, final float alpha) {

        // do nothing
    }

    @Override
    public void setDestination(final IVector destination) {

        x = destination.getX();
        y = destination.getY();
    }

    @Override
    public float getX() {

        return x;
    }

    @Override
    public float getY() {

        return y;
    }

    @Override
    public void setX(final float x) {

        this.x = x;
    }

    @Override
    public void setY(final float y) {

        this.y = y;
    }

    public void setRadius(final float radius) {

        this.radius = radius;
    }

    @Override
    public void setShipEngine(final IEngine engine) {

        // do nothing
    }

    @Override
    public void addWeapon(final IWeapon weapon) {

        // do nothing
    }

    @Override
    public Set<IWeapon> getWeapons() {

        return null;
    }

    @Override
    public float getHeight() {

        return 0;
    }

    @Override
    public float getWidth() {

        return 0;
    }

    @Override
    public boolean takeEnergy(final float energyCost) {

        return false;
    }

    @Override
    public void setEnergyPool(final IPool pool) {

        // do nothing
    }

    @Override
    public IPool getEnergyPool() {

        return null;
    }

    @Override
    public void setTexture(final ITexture texture) {

        // do nothing
    }

    @Override
    public boolean exploded() {

        return false;
    }

    @Override
    public void setHpPool(final IPool pool) {

        hpPool = pool;
    }

    @Override
    public IPool getHpPool() {

        return hpPool;
    }

    @Override
    public void setMissilesLauncher(final MissilesLauncher launcher) {

        // do nothing
    }

    @Override
    public void explode() {

        // do nothing
    }

    @Override
    public void setExplosion(final Launchable explosion) {

        // do nothing
    }

    @Override
    public void ignite(final float fireDmg, final long fireDuration) {

        // do nothing
    }

    @Override
    public void setBurner(final Burner burner) {

        // do nothing
    }

    @Override
    public float getDrawingX() {

        return x;
    }

    @Override
    public float getDrawingY() {

        return y;
    }

    @Override
    public void setPlayerShip(final RadarVisible playerShip) {

        // do nothing
    }

    @Override
    public void setMover(final MoverAI mover) {

        // do nothing
    }

    @Override
    public void setShooter(final ShooterAI shooter) {

        // do nothing
    }

    @Override
    public IWeaponController getWeaponController() {

        return null;
    }

    @Override
    public boolean isMoving() {

        return false;
    }

    @Override
    public void setWeaponController(final IWeaponController controller) {

        // do nothing
    }

    @Override
    public MoverType getMoverType() {

        return null;
    }

    @Override
    public void setBar(final EnemyBar bar) {

        // do nothing
    }

    @Override
    public void setPowerUp(final IPowerUp powerUp) {

        // do nothing
    }

    @Override
    public void setTemporalImmortalityChecker(final BooleanSupplier checker) {

        // do nothing
    }

    @Override
    public boolean isImmortal() {

        return false;
    }

    @Override
    public void freeze(final Freezer freezer) {

        // do nothing
    }

    @Override
    public void unfreeze() {

        // do nothing
    }

    @Override
    public MoverType getDefaultMoverType() {

        return null;
    }

    @Override
    public ShooterType getDefaultShooterType() {

        return null;
    }

    @Override
    public Attributes getAttributes() {

        return null;
    }

    @Override
    public boolean hpBelowHalf() {

        return getHpPool().getAmount() < getHpPool().getMaxAmount() / 2;
    }

    @Override
    public void forceDestination(final IVector destination) {

        // do nothing
    }
}
