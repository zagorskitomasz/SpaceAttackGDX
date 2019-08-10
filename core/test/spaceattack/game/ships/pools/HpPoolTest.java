package spaceattack.game.ships.pools;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.factories.Factories;

public class HpPoolTest {

    private HpPool pool;

    @Before
    public void setUp() {

        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
        pool = new HpPool(10);
    }

    @Test
    public void ifDrawnedToZeroWontRegen() {

        pool.take(60);
        pool.update();

        assertEquals(0, pool.getAmount(), 0);
    }

    @Test
    public void ifDrawnedBelowZeroAmountIsZero() {

        pool.take(100);

        assertEquals(0, pool.getAmount(), 0);
    }

    @Test
    public void regenIsAddingAmount() {

        pool.take(30);
        pool.regen(20);

        assertEquals(50f, pool.getAmount(), 0);
    }

    @Test
    public void wontRegenOverMaxAmount() {

        pool.take(30);
        pool.regen(40);

        assertEquals(60f, pool.getAmount(), 0);
    }

    @Test
    public void immunePoolDoesntTakeDmg() {

        pool.setImmunityChecker(() -> true);
        pool.take(30);

        assertEquals(60f, pool.getAmount(), 0);
    }
}
