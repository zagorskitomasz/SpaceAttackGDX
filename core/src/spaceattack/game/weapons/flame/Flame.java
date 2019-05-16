package spaceattack.game.weapons.flame;

import java.util.function.Predicate;
import java.util.function.Supplier;

import spaceattack.consts.Consts;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.Ignitable;
import spaceattack.game.actors.interfaces.Overheatable;
import spaceattack.game.actors.interfaces.Undestructible;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.missiles.Missile;

public class Flame extends Missile implements Undestructible {

    private IAnimation flameAnimation;
    private Supplier<IVector> positioner;
    private Predicate<Float> activityChecker;
    private float energyCost;
    private long soundInstanceId;

    @Override
    public void draw(final IBatch batch, final float alpha) {

        setTexture(flameAnimation.getFrame());

        ITexture texture = getTexture();
        batch.draw(texture, getDrawingX(), getDrawingY(), texture.getWidth(), texture.getHeight());
    }

    @Override
    public void act(final float delta) {

        if (getTexture() == null && flameAnimation != null) {
            setTexture(flameAnimation.getFrame());
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
            if (actor instanceof Ignitable && differentFraction(actor)) {
                collision((Ignitable) actor);
            }
            if (actor instanceof Overheatable) {
                collision((Overheatable) actor);
            }
        }
    }

    private void collision(final Ignitable ignitable) {

        if (checkCollision(ignitable)) {
            ignitable.ignite(getDmg(), Consts.Weapons.FLAME_DURATION);
        }
    }

    private void collision(final Overheatable overheatable) {

        if (checkCollision(overheatable)) {
            overheatable.overheat();
        }
    }

    @Override
    protected void move() {

        setPosition(positioner.get());
    }

    public void setAnimation(final IAnimation animation) {

        flameAnimation = animation;
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
