package spaceattack.game.powerup;

import spaceattack.game.ships.pools.IPool;

public class PoolIncreaser extends AbstractPowerUp implements IPowerUp {

    private IPool pool;
    private float regenPercent;

    @Override
    public void consumed() {

        pool.regen(pool.getMaxAmount() * regenPercent);
    }

    public void setPool(IPool pool) {

        this.pool = pool;
    }

    public void setIncreasePercent(float regenPercent) {

        this.regenPercent = regenPercent;
    }
}
