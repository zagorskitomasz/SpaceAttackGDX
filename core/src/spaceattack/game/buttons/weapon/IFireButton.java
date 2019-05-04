package spaceattack.game.buttons.weapon;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.weapons.IWeapon;

public interface IFireButton extends IGameActor {

    @Override
    default void setActor(final IActor actor) {

        // do nothing
    }

    @Override
    default void draw(final IBatch batch, final float alpha) {

        // do nothing
    }

    @Override
    default void act(final float delta) {

        // do nothing
    }

    void setWeapon(IWeapon secondaryWeapon);

    void setEnergyPool(IPool pool);

    boolean touchDown(int screenX, int screenY);

    boolean touchUp(int screenX, int screenY);

    boolean isPressed();
}
