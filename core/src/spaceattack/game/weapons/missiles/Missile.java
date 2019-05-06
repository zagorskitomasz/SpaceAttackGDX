package spaceattack.game.weapons.missiles;

import java.util.List;

import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.Freezable;
import spaceattack.game.actors.interfaces.Vulnerable;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.player.PlayerShip;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;

public class Missile extends AbstractMissile implements Freezable {

    protected Iterable<IGameActor> actors;

    private ITexture texture;
    private float dmg;
    private float speed;
    private float acceleration;
    private IVector movement;
    private Freezer freezer;

    @Override
    public void setActors(final List<IGameActor> iterable) {

        this.actors = iterable;
    }

    @Override
    public void act(final float delta) {

        if (freezer == null) {
            move();
        }
        else {
            freezer.act(delta);
        }

        if (actors == null) {
            return;
        }

        for (IGameActor actor : actors) {
            if (actor instanceof Vulnerable && differentFraction(actor)) {
                collision((Vulnerable) actor);
            }
        }
    }

    protected boolean differentFraction(final IGameActor actor) {

        return (isPlayersAttack && actor instanceof IEnemyShip) || (!isPlayersAttack && actor instanceof PlayerShip);
    }

    protected void move() {

        getActor().setX(getActor().getX() + movement.getX() * speed);
        getActor().setY(getActor().getY() + movement.getY() * speed);

        speed += acceleration;
    }

    private void collision(final Vulnerable vulnerable) {

        if (!isToKill() && checkCollision(vulnerable)) {
            vulnerable.takeDmg(dmg);
            setToKill();
        }
    }

    @Override
    protected ITexture getTexture() {

        return texture;
    }

    public void setTexture(final ITexture texture) {

        this.texture = texture;
    }

    public void setDmg(final float dmg) {

        this.dmg = dmg;
    }

    public void setSpeed(final float speed) {

        this.speed = speed;
    }

    public void setAcceleration(final float acceleration) {

        this.acceleration = acceleration;
    }

    public void setMovement(final IVector movement) {

        if (movement != null) {
            this.movement = movement.normalize();
        }
    }

    public float getDmg() {

        return dmg;
    }

    public float getSpeed() {

        return speed;
    }

    public float getAcceleration() {

        return acceleration;
    }

    @Override
    public void launched() {

        playSound();
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
    public float getWidth() {

        return getTexture().getWidth();
    }

    @Override
    public float getHeight() {

        return getTexture().getHeight();
    }
}
