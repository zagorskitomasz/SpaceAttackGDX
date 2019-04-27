package spaceattack.game.ships.pools;

import spaceattack.game.system.notifiers.INotifier;

public interface IPool extends INotifier<Float> {

    public boolean take(float amount);

    public float getAmount();

    public float getMaxAmount();

    public void setLevel(int level);

    public void update();

    public void destroy();

    public void regen(float amount);
}
