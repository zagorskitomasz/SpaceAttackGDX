package com.zagorskidev.spaceattack.ships;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HpPoolTest
{
	private static final float BASE_AMOUNT = 50;
	private static final float INCREASE_PER_LEVEL = 20;
	private static final float BASE_REGEN = 10 * Pool.UPDATES_PER_SECOND;
	private static final float REGEN_PER_LEVEL = 5 * Pool.UPDATES_PER_SECOND;

	private HpPool pool;

	@Before
	public void setUp()
	{
		pool = new HpPool(BASE_AMOUNT, INCREASE_PER_LEVEL, BASE_REGEN, REGEN_PER_LEVEL);
	}

	@Test
	public void ifDrawnedToZeroWontRegen()
	{
		pool.take(50);
		pool.update();

		assertEquals(0, pool.getAmount(), 0);
	}

	@Test
	public void ifDrawnedBelowZeroAmountIsZero()
	{
		pool.take(100);

		assertEquals(0, pool.getAmount(), 0);
	}
}
