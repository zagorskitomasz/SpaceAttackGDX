package spaceattack.game.weapons.timeWave;

import java.util.function.Supplier;

import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.Freezable;
import spaceattack.game.actors.interfaces.Undestructible;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.missiles.FreezerBuilder;
import spaceattack.game.weapons.missiles.Missile;

public class TimeWave extends Missile implements Undestructible {

    private IAnimation animation;
    private Supplier<IVector> positioner;

    @Override
    public void act(final float delta) {

        if (animation != null && animation.isCompleted()) {
            setToKill();
        }

        if (getTexture() == null && animation != null) {
            setTexture(animation.getFrame());
        }
        move();
    }

    @Override
    public void launched() {

        playSound();

        if (actors == null) {
            return;
        }

        for (IGameActor actor : actors) {
            if (actor instanceof Freezable && differentFraction(actor)) {
                ((Freezable) actor).freeze(FreezerBuilder.INSTANCE.build((Freezable) actor, (long) getDmg()));
            }
        }
    }

    public void draw(final IBatch batch) {

        super.draw(batch, 1);
    }

    @Override
    protected void move() {

        setPosition(positioner.get());
    }

    public void setAnimation(final IAnimation animation) {

        this.animation = animation;
    }

    public void setPositionSupplier(final Supplier<IVector> supplier) {

        positioner = supplier;
    }
}
