package spaceattack.game.ships.pools;

import java.util.function.Supplier;

import spaceattack.consts.Consts;

public class HpPool extends Pool {

    private Supplier<Boolean> immunityChecker;

    public HpPool(final int shields) {

        this(shields, 0);
    }

    public HpPool(final int shields, final int mastery) {

        super(shields, mastery);

        this.maxAmount = Consts.Pools.HP_PER_ATTR * shields;
        this.regenPerSecond = Consts.Pools.HP_REGEN_PER_ATTR * shields
                * (1 + Consts.Pools.REGEN_MASTERY_FACTOR * mastery);
        this.amount = maxAmount;
    }

    public void setImmunityChecker(final Supplier<Boolean> supplier) {

        immunityChecker = supplier;
    }

    @Override
    boolean doTake(final float amountTaken) {

        if (immunityChecker != null && immunityChecker.get()) {
            return true;
        }

        if (isInfinity()) {
            return true;
        }
        absorb(amountTaken);
        boolean result;

        if (amountTaken < amount) {
            amount -= amountTaken;
            result = true;
        }
        else {
            destroy();
            result = false;
        }
        notifyObservers();
        return result;
    }
}
