package spaceattack.game.ships.pools;

import java.util.function.Supplier;

public class HpPool extends Pool {

    private Supplier<Boolean> immunityChecker;

    public HpPool(final float baseAmount, final float increasePerLevel, final float baseRegen,
            final float regenPerLevel) {

        super(baseAmount, increasePerLevel, baseRegen, regenPerLevel);
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
