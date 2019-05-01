package spaceattack.game.weapons.missiles;

import spaceattack.consts.Sizes;
import spaceattack.game.actors.DrawableActor;
import spaceattack.game.actors.interfaces.Collisionable;
import spaceattack.game.actors.interfaces.Killable;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.NumbersUtils;
import spaceattack.game.utils.vector.IVector;

public abstract class AbstractMissile extends DrawableActor implements Launchable, Killable, Collisionable {

    private boolean isToKill;
    protected Sounds sound;
    private float radius;
    protected boolean isPlayersAttack;

    protected boolean checkCollision(final Collisionable collisionable) {

        disappearIfNeeded();

        IVector missileCenter = Factories.getVectorFactory().create(getActor().getX(), getActor().getY());
        IVector vulnerableCenter = collisionable.getPosition();

        return NumbersUtils.distance(missileCenter, vulnerableCenter) <= collisionable.getRadius() + getRadius();
    }

    @Override
    public float getRadius() {

        return radius * Sizes.RADIUS_FACTOR;
    }

    public void setRadius(final float radius) {

        this.radius = radius;
    }

    @Override
    public IVector getPosition() {

        return Factories.getVectorFactory().create(getActor().getX(), getActor().getY());
    }

    @Override
    public void setToKill() {

        isToKill = true;
    }

    @Override
    public boolean isToKill() {

        return isToKill;
    }

    public void setSound(final Sounds sound) {

        this.sound = sound;
    }

    @Override
    public void playSound() {

        if (sound != null) {
            sound.play();
        }
    }

    public void setPosition(final IVector position) {

        if (getActor() != null) {
            getActor().setX(position.getX());
            getActor().setY(position.getY());
        }
    }

    public void setPlayersAttack(final boolean isPlayer) {

        isPlayersAttack = isPlayer;
    }
}
