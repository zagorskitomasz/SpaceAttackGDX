package spaceattack.game.ships.pools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.function.BooleanSupplier;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.notifiers.IObserver;

public class PoolTest {

    private static final float BASE_AMOUNT = 50;
    private static final float INCREASE_PER_LEVEL = 20;
    private static final float BASE_REGEN = 10 * Pool.UPDATES_PER_SECOND;
    private static final float REGEN_PER_LEVEL = 5 * Pool.UPDATES_PER_SECOND;

    private Pool pool;

    @Mock
    private BooleanSupplier infinityChecker;

    @Mock
    private BooleanSupplier nextInfinityChecker;

    @Mock
    private IObserver<Float> observer;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
        pool = new Pool(BASE_AMOUNT, INCREASE_PER_LEVEL, BASE_REGEN, REGEN_PER_LEVEL);
    }

    @Test
    public void defaultValues() {

        assertEquals(50, pool.getAmount(), 0);
        assertEquals(50, pool.getMaxAmount(), 0);
    }

    @Test
    public void increasingLevel() {

        pool.setLevel(3);

        assertEquals(90, pool.getAmount(), 0);
        assertEquals(90, pool.getMaxAmount(), 0);
    }

    @Test
    public void takingIsDecreasingAmount() {

        pool.take(15);

        assertEquals(35, pool.getAmount(), 0);
        assertEquals(50, pool.getMaxAmount(), 0);
    }

    @Test
    public void succesfullTakeReturnsTrue() {

        assertTrue(pool.take(15));
    }

    @Test
    public void failedTakeReturnsFalse() {

        assertFalse(pool.take(65));
    }

    @Test
    public void failedTakeDontDecreasePool() {

        pool.take(65);

        assertEquals(50, pool.getAmount(), 0);
        assertEquals(50, pool.getMaxAmount(), 0);
    }

    @Test
    public void updatingRegeneratesPool() {

        pool.take(15);
        pool.update();

        assertEquals(45, pool.getAmount(), 0);
        assertEquals(50, pool.getMaxAmount(), 0);
    }

    @Test
    public void updatingIsIntervalWork() {

        pool.take(45);
        pool.update();
        pool.update();

        assertEquals(15, pool.getAmount(), 0);
        assertEquals(50, pool.getMaxAmount(), 0);
    }

    @Test
    public void updatingIsPossibleAfterFixedTime() throws InterruptedException {

        pool.take(45);
        pool.update();
        Thread.sleep(1000 / Pool.UPDATES_PER_SECOND + 10);
        pool.update();

        assertEquals(25, pool.getAmount(), 0);
        assertEquals(50, pool.getMaxAmount(), 0);
    }

    @Test
    public void regenIsIncreasingEveryLevel() {

        pool.setLevel(3);
        pool.take(70);
        pool.update();

        assertEquals(40, pool.getAmount(), 0);
        assertEquals(90, pool.getMaxAmount(), 0);
    }

    @Test
    public void observerIsNotifiedWithPoolPercentAfterTake() {

        pool.registerObserver(observer);
        pool.take(10);

        verify(observer).notify(0.8f);
    }

    @Test
    public void observerIsNotifiedWithPoolPercentAfterLevelSet() {

        pool.registerObserver(observer);
        pool.setLevel(3);

        verify(observer).notify(1f);
    }

    @Test
    public void observerIsNotifiedWithPoolPercentAfterUpdate() {

        pool.take(30);
        pool.registerObserver(observer);
        pool.update();

        verify(observer).notify(0.6f);
    }

    @Test
    public void unregisteredObserverWontBeNotified() {

        pool.registerObserver(observer);
        pool.unregisterObserver(observer);
        pool.take(30);

        verify(observer, times(0)).notify(anyFloat());
    }

    @Test
    public void cantRegenerateMoreThanMaxAmount() {

        pool.update();

        assertEquals(50, pool.getAmount(), 0);
        assertEquals(50, pool.getMaxAmount(), 0);
    }

    @Test
    public void afterDestroyingPoolIsEmptyAndDoesntRegen() {

        pool.destroy();
        pool.update();

        assertEquals(0, pool.getAmount(), 0);
        assertEquals(50, pool.getMaxAmount(), 0);
    }

    @Test
    public void whenInfinityCheckerIsUpAmountIsNotDecreased() {

        pool.addTemporalInfinityChecker(infinityChecker);
        doReturn(true).when(infinityChecker).getAsBoolean();

        pool.take(15);

        assertEquals(50, pool.getAmount(), 0);
    }

    @Test
    public void whenInfinityCheckerIsNoLongerUpAmountIsDecreased() {

        pool.addTemporalInfinityChecker(infinityChecker);
        doReturn(true, false).when(infinityChecker).getAsBoolean();

        pool.take(15);
        pool.take(12);

        assertEquals(38, pool.getAmount(), 0);
    }

    @Test
    public void whenInfinityCheckerIsUpAgainAmountIsDecreased() {

        pool.addTemporalInfinityChecker(infinityChecker);
        doReturn(true, false, true).when(infinityChecker).getAsBoolean();

        pool.take(15);
        pool.take(12);
        pool.take(12);

        assertEquals(26, pool.getAmount(), 0);
    }

    @Test
    public void whenInfinityCheckerIsNoLongerUpButNextCheckerIsUpAmountIsNotDecreased() {

        pool.addTemporalInfinityChecker(infinityChecker);
        doReturn(true, false).when(infinityChecker).getAsBoolean();

        pool.take(15);

        pool.addTemporalInfinityChecker(nextInfinityChecker);
        doReturn(true).when(nextInfinityChecker).getAsBoolean();

        pool.take(12);

        assertEquals(50, pool.getAmount(), 0);
    }
}
