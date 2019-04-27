package spaceattack.game.buttons.weapon;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.weapons.IWeapon;

public interface IFireButton extends IGameActor {

    @Override
    public default void setActor(IActor actor) {

        // do nothing
    }

    @Override
    public default void draw(IBatch batch, float alpha) {

        // do nothing
    }

    @Override
    public default void act(float delta) {

        // do nothing
    }

    public void setWeapon(IWeapon secondaryWeapon);

    public void setEnergyPool(IPool pool);

    public boolean touchDown(int screenX, int screenY);

    public boolean touchUp(int screenX, int screenY);
}
