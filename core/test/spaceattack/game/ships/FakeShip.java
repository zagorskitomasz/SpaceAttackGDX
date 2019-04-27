package spaceattack.game.ships;

import java.util.Set;

import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.GameProgress;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.batch.IBatch;
import spaceattack.game.engines.IEngine;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.ships.enemy.EnemyBar;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Burner;

public class FakeShip implements IShip, IEnemyShip {

    private float x;
    private float y;
    private float radius;
    private IPool hpPool;

    @Override
    public void notify(GameProgress state) {

        // do nothing
    }

    @Override
    public void takeDmg(float dmg) {

        // do nothing
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

        // do nothing
    }

    @Override
    public boolean isToKill() {

        return false;
    }

    @Override
    public IActor getActor() {

        return null;
    }

    @Override
    public void setActor(IActor actor) {

        // do nothing
    }

    @Override
    public void act(float delta) {

        // do nothing
    }

    @Override
    public void draw(IBatch batch, float alpha) {

        // do nothing
    }

    @Override
    public void setDestination(IVector destination) {

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
    public void setX(float x) {

        this.x = x;
    }

    @Override
    public void setY(float y) {

        this.y = y;
    }

    public void setRadius(float radius) {

        this.radius = radius;
    }

    @Override
    public void setShipEngine(IEngine engine) {

        // do nothing
    }

    @Override
    public void addWeapon(IWeapon weapon) {

        // do nothing
    }

    @Override
    public Set<IWeapon> getWeapons() {

        return null;
    }

    @Override
    public void setLevel(int level) {

        // do nothing
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
    public boolean takeEnergy(float energyCost) {

        return false;
    }

    @Override
    public void setEnergyPool(IPool pool) {

        // do nothing
    }

    @Override
    public IPool getEnergyPool() {

        return null;
    }

    @Override
    public void setTexture(ITexture texture) {

        // do nothing
    }

    @Override
    public boolean exploded() {

        return false;
    }

    @Override
    public void setHpPool(IPool pool) {

        hpPool = pool;
    }

    @Override
    public IPool getHpPool() {

        return hpPool;
    }

    @Override
    public void setMissilesLauncher(MissilesLauncher launcher) {

        // do nothing
    }

    @Override
    public void explode() {

        // do nothing
    }

    @Override
    public void setExplosion(Launchable explosion) {

        // do nothing
    }

    @Override
    public void ignite(float fireDmg, long fireDuration) {

        // do nothing
    }

    @Override
    public void setBurner(Burner burner) {

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
    public void setPlayerShip(RadarVisible playerShip) {

        // do nothing
    }

    @Override
    public void setMover(MoverAI mover) {

        // do nothing
    }

    @Override
    public void setShooter(ShooterAI shooter) {

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
    public void setWeaponController(IWeaponController controller) {

        // do nothing
    }

    @Override
    public MoverType getMoverType() {

        return null;
    }

    @Override
    public void setBar(EnemyBar bar) {

        // do nothing
    }

    @Override
    public void setPowerUp(IPowerUp powerUp) {

        // do nothing
    }
}
