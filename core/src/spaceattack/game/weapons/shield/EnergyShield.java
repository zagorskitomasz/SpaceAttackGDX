package spaceattack.game.weapons.shield;

import java.util.function.Predicate;
import java.util.function.Supplier;

import spaceattack.consts.Consts;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.Undestructible;
import spaceattack.game.actors.interfaces.Vulnerable;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.missiles.Missile;

public class EnergyShield extends Missile implements Undestructible {

    private IAnimation shieldAnimation;
    private Supplier<IVector> positioner;
    private Predicate<Float> activityChecker;
    private float energyCost;
    private long soundInstanceId;

    @Override
    public void draw(final IBatch batch, final float alpha) {

        setTexture(shieldAnimation.getFrame());

        ITexture texture = getTexture();
        batch.draw(texture, getDrawingX(), getDrawingY(), texture.getWidth(), texture.getHeight());
    }

    @Override
    public void act(final float delta) {

        if (getTexture() == null && shieldAnimation != null) {
            setTexture(shieldAnimation.getFrame());
        }
        move();
        if (!activityChecker.test(energyCost)) {
            sound.stop(soundInstanceId);
            setToKill();
        }

        if (actors == null) {
            return;
        }

        for (IGameActor actor : actors) {
            if (actor instanceof Vulnerable && differentFraction(actor)) {
                collision((Vulnerable) actor);
            }
            if (actor instanceof Missile && !(actor instanceof Undestructible)) {
                destroy((Missile) actor);
            }
        }
    }

    private void collision(final Vulnerable vulnerable) {

        if (checkCollision(vulnerable)) {
            vulnerable.takeDmg(getDmg());
        }
    }

    @Override
    public float getDmg() {

        return super.getDmg() / Consts.Metagame.FPS;
    }

    private void destroy(final Missile missile) {

        if (checkCollision(missile) && !missile.isToKill()) {
            missile.setToKill();
        }
    }

    @Override
    protected void move() {

        setPosition(positioner.get());
    }

    public void setAnimation(final IAnimation animation) {

        shieldAnimation = animation;
    }

    public void setPositionSupplier(final Supplier<IVector> supplier) {

        positioner = supplier;
    }

    public void setActivityPredicate(final Predicate<Float> predicate) {

        activityChecker = predicate;
    }

    public void setEnergyCost(final float energyCost) {

        this.energyCost = energyCost;
    }

    public boolean isUp() {

        return !isToKill();
    }

    @Override
    public void playSound() {

        if (sound != null) {
            soundInstanceId = sound.loop();
        }
    }
}
