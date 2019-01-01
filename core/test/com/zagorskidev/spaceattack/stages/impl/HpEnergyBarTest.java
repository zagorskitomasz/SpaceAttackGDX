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
	private IPool energyPool;
	private IPool hpPool;

	@Before
	public void setUp()
	{
		Textures.loadForTest();

		energyPool = new Pool(50, 10, 5 * Pool.UPDATES_PER_SECOND, 5 * Pool.UPDATES_PER_SECOND);
		hpPool = new Pool(80, 20, 3 * Pool.UPDATES_PER_SECOND, 3 * Pool.UPDATES_PER_SECOND);
		bar = new HpEnergyBar(energyPool, hpPool);
	}

	@Test
	public void modifyingEnergyPoolIsAppliedOnBar()
	{
		energyPool.setLevel(3);
		energyPool.take(40);
		energyPool.update();

		assertEquals(70, bar.getMaxEnergy(), 0);
		assertEquals(45, bar.getEnergy(), 0);
		assertEquals(0.64, bar.getEnergyPercent(), 0.01);
	}

	@Test
	public void modifyinHpPoolIsAppliedOnBar()
	{
		hpPool.setLevel(3);
		hpPool.take(40);
		hpPool.update();

		assertEquals(120, bar.getMaxHp(), 0);
		assertEquals(89, bar.getHp(), 0);
		assertEquals(0.74, bar.getHpPercent(), 0.01);
	}
}
