package spaceattack.game.bars;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.actors.ILabel;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.ships.pools.Pool;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.IUtilsFactory;

public class HpEnergyBarTest
{
	private HpEnergyBar bar;
	private IPool energyPool;
	private IPool hpPool;

	@Mock
	private IUtilsFactory utilsFactory;

	private IUtils utils;

	@Mock
	private ILabel label;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		utils = spy(ExtUtilsFactory.INSTANCE.create());
		doReturn(utils).when(utilsFactory).create();
		doReturn(label).when(utils).createBarLabel();
		doCallRealMethod().when(utils).millis();

		Textures.loadForTest();
		Factories.setUtilsFactory(utilsFactory);

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
