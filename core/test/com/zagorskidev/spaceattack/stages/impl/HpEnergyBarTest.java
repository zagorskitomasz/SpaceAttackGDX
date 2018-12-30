package com.zagorskidev.spaceattack.stages.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.ships.Pool;

public class HpEnergyBarTest
{
	private HpEnergyBar bar;
	private IPool pool;

	@Before
	public void setUp()
	{
		Textures.loadForTest();

		pool = new Pool(50, 10, 5 * Pool.UPDATES_PER_SECOND, 5 * Pool.UPDATES_PER_SECOND);
		bar = new HpEnergyBar(pool);
	}

	@Test
	public void modifyingPoolIsAppliedOnBar()
	{
		pool.setLevel(3);
		pool.take(40);
		pool.update();

		assertEquals(70, bar.getMaxEnergy(), 0);
		assertEquals(45, bar.getEnergy(), 0);
		assertEquals(0.64, bar.getEnergyPercent(), 0.01);
	}
}
