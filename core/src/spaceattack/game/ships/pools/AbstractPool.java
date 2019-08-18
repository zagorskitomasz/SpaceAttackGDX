package spaceattack.game.ships.pools;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import spaceattack.game.system.notifiers.IObserver;

public abstract class AbstractPool implements IPool {

    private final Set<IObserver<Float>> observers;

    protected float maxAmount;
    protected float amount;

    protected Consumer<Float> absorber;
    protected float absorbingFactor;

    public AbstractPool() {

        observers = new HashSet<>();
    }

    @Override
    public void registerObserver(final IObserver<Float> observer) {

        observers.add(observer);
    }

    @Override
    public void unregisterObserver(final IObserver<Float> observer) {

        if (observers == null) {
            return;
        }

        observers.remove(observer);
    }

    protected void notifyObservers() {

        if (observers == null || observers.size() <= 0) {
            return;
        }

        float poolPercent = amount / maxAmount;

        for (IObserver<Float> observer : observers) {
            observer.notify(poolPercent);
        }
    }

    @Override
    public float getAmount() {

        return amount;
    }

    @Override
    public float getMaxAmount() {

        return maxAmount;
    }

    @Override
    public void regen(final float amount) {

        this.amount = Math.min(this.amount + amount, maxAmount);
    }

    @Override
    public void setAbsorber(final Consumer<Float> absorber, final float factor) {

        this.absorber = absorber;
        this.absorbingFactor = factor;
    }
}
