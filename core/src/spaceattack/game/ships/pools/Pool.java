package spaceattack.game.ships.pools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.FrameController;

public class Pool extends AbstractPool {

    public static final Integer UPDATES_PER_SECOND = 10;

    private final float baseAmount;
    private final float increasePerLevel;
    private final float baseRegen;
    private final float regenPerLevel;

    private float regenPerSecond;

    private boolean destroyed;

    private final Lock lock;
    private final FrameController controller;

    private final List<BooleanSupplier> infinityCheckers;

    public Pool(final float baseAmount, final float increasePerLevel, final float baseRegen,
            final float regenPerLevel) {

        super();
        infinityCheckers = new ArrayList<>();
        lock = new ReentrantLock();
        controller = new FrameController(Factories.getUtilsFactory().create(), UPDATES_PER_SECOND);

        this.baseAmount = baseAmount;
        this.increasePerLevel = increasePerLevel;
        this.baseRegen = baseRegen;
        this.regenPerLevel = regenPerLevel;

        setLevel(1);
    }

    @Override
    public boolean take(final float amountTaken) {

        try {
            lock.lock();
            return doTake(amountTaken);
        }
        finally {
            lock.unlock();
        }
    }

    boolean doTake(final float amountTaken) {

        if (isInfinity()) {
            return true;
        }
        if (amountTaken <= amount) {
            amount -= amountTaken;
            notifyObservers();

            return true;
        }
        return false;
    }

    @Override
    public boolean isInfinity() {

        infinityCheckers.removeIf(checker -> !checker.getAsBoolean());
        return !infinityCheckers.isEmpty();
    }

    @Override
    public void setLevel(final int level) {

        try {
            lock.lock();
            doSetLevel(level);
        }
        finally {
            lock.unlock();
        }
    }

    void doSetLevel(final int level) {

        maxAmount = baseAmount + (level - 1) * increasePerLevel;
        regenPerSecond = baseRegen + (level - 1) * regenPerLevel;

        amount = maxAmount;

        notifyObservers();
    }

    @Override
    public void update() {

        try {
            lock.lock();
            doUpdate();
        }
        finally {
            lock.unlock();
        }
    }

    void doUpdate() {

        if (controller.check() && !destroyed) {
            amount += regenPerSecond / UPDATES_PER_SECOND;

            if (amount > maxAmount) {
                amount = maxAmount;
            }

            notifyObservers();
        }
    }

    @Override
    public void destroy() {

        destroyed = true;
        amount = 0;
        notifyObservers();
    }

    @Override
    public void addTemporalInfinityChecker(final BooleanSupplier checker) {

        infinityCheckers.add(checker);
    }
}
