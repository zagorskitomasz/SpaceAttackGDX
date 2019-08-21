package spaceattack.game.ships;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.BooleanSupplier;

import spaceattack.game.actors.DrawableActor;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.batch.IBatch;
import spaceattack.game.engines.IEngine;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Improvement;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.Freezer;

public abstract class Ship extends DrawableActor implements IShip {

    protected ITexture currentTexture;

    protected IEngine engine;
    protected Set<IWeapon> weapons;

    private IPool energyPool;
    private IPool hpPool;

    private boolean isToKill;
    protected boolean exploded;

    protected MissilesLauncher launcher;
    private Launchable explosion;

    private Burner burner;
    private Freezer freezer;

    private final ITexture adrenalineEffect = Textures.ADRENALINE.getTexture();

    private final ITexture zerk1Effect = Textures.ZERK1.getTexture();
    private final ITexture zerk2Effect = Textures.ZERK2.getTexture();
    private final ITexture zerk3Effect = Textures.ZERK3.getTexture();

    private Runnable berserkGainer;

    @Override
    public void setTexture(final ITexture texture) {

        currentTexture = texture;
    }

    @Override
    public void setShipEngine(final IEngine engine) {

        this.engine = engine;
    }

    @Override
    protected ITexture getTexture() {

        return currentTexture;
    }

    @Override
    public void setDestination(final IVector destination) {

        if (engine != null) {
            engine.setDestination(destination);
        }
    }

    @Override
    public void forceDestination(final IVector destination) {

        if (engine != null) {
            engine.forceDestination(destination);
        }
    }

    @Override
    public void setEnergyPool(final IPool pool) {

        energyPool = pool;
    }

    @Override
    public void setHpPool(final IPool pool) {

        hpPool = pool;
    }

    @Override
    public void setBurner(final Burner burner) {

        this.burner = burner;
    }

    @Override
    public void act(final float delta) {

        if (energyPool != null) {
            energyPool.update();
        }

        if (hpPool != null) {
            hpPool.update();
            if (burner != null) {
                burner.burn(delta);
            }
        }

        if (engine != null && freezer == null) {
            fly();
        }

        if (freezer != null) {
            freezer.act(delta);
        }
    }

    protected void fly() {

        engine.fly();
    }

    @Override
    public void addWeapon(final IWeapon weapon) {

        if (weapons == null) {
            weapons = new CopyOnWriteArraySet<>();
        }

        weapons.add(weapon);
    }

    @Override
    public Set<IWeapon> getWeapons() {

        return weapons;
    }

    @Override
    public float getHeight() {

        return currentTexture != null ? currentTexture.getHeight() : 0;
    }

    @Override
    public float getWidth() {

        return currentTexture != null ? currentTexture.getWidth() : 0;
    }

    @Override
    public void takeDmg(final float dmg) {

        if (hpPool != null && !hpPool.take(dmg)) {
            setToKill();
            energyPool.destroy();
        }
    }

    @Override
    public float getRadius() {

        return (getHeight() + getWidth()) * 0.25f;
    }

    @Override
    public IVector getPosition() {

        return Factories.getVectorFactory().create(getX(), getY());
    }

    @Override
    public void setToKill() {

        if (!isToKill) {
            isToKill = true;
            if (!isOutOfScreen()) {
                if (berserkGainer != null) {
                    berserkGainer.run();
                }
                explode();
            }
        }
    }

    @Override
    public boolean isToKill() {

        return isToKill;
    }

    @Override
    public boolean takeEnergy(final float energyCost) {

        return energyPool != null ? energyPool.take(energyCost) : true;
    }

    @Override
    public IPool getEnergyPool() {

        return energyPool;
    }

    @Override
    public IPool getHpPool() {

        return hpPool;
    }

    @Override
    public void setMissilesLauncher(final MissilesLauncher launcher) {

        this.launcher = launcher;
    }

    @Override
    public void explode() {

        if (launcher == null || explosion == null) {
            return;
        }

        exploded = true;
        IActor actor = getActor();
        explosion.getActor().setPosition(actor.getX(), actor.getY());
        launcher.launch(explosion);
    }

    @Override
    public void setExplosion(final Launchable explosion) {

        this.explosion = explosion;
    }

    @Override
    public void draw(final IBatch batch, final float alpha) {

        super.draw(batch, alpha);

        drawZerk(batch);
        drawAdrenaline(batch);

        if (burner != null) {
            burner.draw(batch);
        }

        if (freezer != null) {
            freezer.draw(batch);
        }
    }

    private void drawAdrenaline(final IBatch batch) {

        if (adrenalined()) {
            drawTexture(batch, adrenalineEffect);
        }
    }

    private void drawZerk(final IBatch batch) {

        switch (getBerserkerLevel()) {
        case 1:
            drawTexture(batch, zerk1Effect);
            break;
        case 2:
            drawTexture(batch, zerk2Effect);
            break;
        case 3:
            drawTexture(batch, zerk3Effect);
            break;
        }
    }

    protected void drawTexture(final IBatch batch, final ITexture texture) {

        batch.draw(texture, getX() - texture.getWidth() / 2,
                getY() - texture.getHeight() / 2);
    }

    private boolean adrenalined() {

        return hpBelowHalf() && getImprovements() != null && getImprovements().get(Improvement.ADRENALINE) > 0;
    }

    @Override
    public void ignite(final float burningDPS, final long fireDuration) {

        if (burner != null) {
            burner.ignite(burningDPS, fireDuration);
        }
    }

    @Override
    public boolean exploded() {

        return exploded;
    }

    @Override
    public void setTemporalImmortalityChecker(final BooleanSupplier checker) {

        hpPool.addTemporalInfinityChecker(checker);
    }

    @Override
    public boolean isImmortal() {

        return hpPool.isInfinity();
    }

    @Override
    public void freeze(final Freezer freezer) {

        this.freezer = freezer;
    }

    @Override
    public void unfreeze() {

        freezer = null;
    }

    @Override
    public boolean hpBelowHalf() {

        return getHpPool() != null && getHpPool().getAmount() < getHpPool().getMaxAmount() / 2;
    }

    @Override
    public void setDestroyAction(final Runnable berserkGainer) {

        this.berserkGainer = berserkGainer;
    }
}
