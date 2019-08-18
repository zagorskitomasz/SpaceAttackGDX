package spaceattack.game.ships.pools;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import spaceattack.game.system.notifiers.INotifier;

public interface IPool extends INotifier<Float> {

    boolean take(float amount);

    float getAmount();

    float getMaxAmount();

    void update();

    void destroy();

    void regen(float amount);

    void addTemporalInfinityChecker(BooleanSupplier checker);

    boolean isInfinity();

    void setAbsorber(Consumer<Float> absorber, float factor);
}
