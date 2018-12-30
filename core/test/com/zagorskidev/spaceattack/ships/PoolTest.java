package com.zagorskidev.spaceattack.ships;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.zagorskidev.spaceattack.notifiers.IObserver;

public class PoolTest
{
	private static final float BASE_AMOUNT = 50;
	private static final float INCREASE_PER_LEVEL = 20;
	private static final float BASE_REGEN = 10 * Pool.UPDATES_PER_SECOND;
	private static final float REGEN_PER_LEVEL = 5 * Pool.UPDATES_PER_SECOND;

	private Pool pool;

	@Mock
	private IObserver<Float> observer;

	@Before
	public void setUp()
	{
		pool = new Pool(BASE_AMOUNT, INCREASE_PER_LEVEL, BASE_REGEN, REGEN_PER_LEVEL);
	}

	@Test
	public void defaultValues()
	{
		assertEquals(50, pool.getAmount(), 0);
		assertEquals(50, pool.getMaxAmount(), 0);
	}

	@Test
	public void increasingLevel()
	{
		pool.setLevel(3);

		assertEquals(90, pool.getAmount(), 0);
		assertEquals(90, pool.getMaxAmount(), 0);
	}

	@Test
	public void takingIsDecreasingAmount()
	{
		pool.take(15);

		assertEquals(35, pool.getAmount(), 0);
		assertEquals(50, pool.getMaxAmount(), 0);
	}

	@Test
	public void succesfullTakeReturnsTrue()
	{
		assertTrue(pool.take(15));
	}

	@Test
	public void failedTakeReturnsFalse()
	{
		assertFalse(pool.take(65));
	}

	@Test
	public void failedTakeDontDecreasePool()
	{
		pool.take(65);

		assertEquals(50, pool.getAmount(), 0);
		assertEquals(50, pool.getMaxAmount(), 0);
	}

	@Test
	public void updatingRegeneratesPool()
	{
		pool.take(15);
		pool.update();

		assertEquals(45, pool.getAmount(), 0);
		assertEquals(50, pool.getMaxAmount(), 0);
	}

	@Test
	public void updatingIsIntervalWork()
	{
		pool.take(45);
		pool.update();
		pool.update();

		assertEquals(15, pool.getAmount(), 0);
		assertEquals(50, pool.getMaxAmount(), 0);
	}

	@Test
	public void updatingIsPossibleAfterFixedTime() throws InterruptedException
	{
		pool.take(45);
		pool.update();
		Thread.sleep(1000 / Pool.UPDATES_PER_SECOND + 10);
		pool.update();

		assertEquals(25, pool.getAmount(), 0);
		assertEquals(50, pool.getMaxAmount(), 0);
	}

	@Test
	public void regenIsIncreasingEveryLevel()
	{
		pool.setLevel(3);
		pool.take(70);
		pool.update();

		assertEquals(40, pool.getAmount(), 0);
		assertEquals(90, pool.getMaxAmount(), 0);
	}

	@Test
	public void observerIsNotifiedWithPoolPercentAfterTake()
	{
		MockitoAnnotations.initMocks(this);

		pool.registerObserver(observer);
		pool.take(10);

		verify(observer).notify(0.8f);
	}

	@Test
	public void observerIsNotifiedWithPoolPercentAfterLevelSet()
	{
		MockitoAnnotations.initMocks(this);

		pool.registerObserver(observer);
		pool.setLevel(3);

		verify(observer).notify(1f);
	}

	@Test
	public void observerIsNotifiedWithPoolPercentAfterUpdate()
	{
		MockitoAnnotations.initMocks(this);

		pool.take(30);
		pool.registerObserver(observer);
		pool.update();

		verify(observer).notify(0.6f);
	}

	@Test
	public void unregisteredObserverWontBeNotified()
	{
		MockitoAnnotations.initMocks(this);

		pool.registerObserver(observer);
		pool.unregisterObserver(observer);
		pool.take(30);

		verify(observer, times(0)).notify(anyFloat());
	}

	@Test
	public void cantRegenerateMoreThanMaxAmount()
	{
		pool.update();

		assertEquals(50, pool.getAmount(), 0);
		assertEquals(50, pool.getMaxAmount(), 0);
	}
}
