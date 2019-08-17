package spaceattack.game.bars;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.actors.ILabel;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.ships.pools.Pool;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.IUtils;

public class HpEnergyBarTest {

    private HpEnergyBar bar;
    private IPool energyPool;
    private IPool hpPool;

    @Mock
    private IUtils utils;

    @Mock
    private ILabel label;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
        doReturn(label).when(utils).createBarLabel();

        Textures.loadForTest();

        energyPool = new Pool(10);
        hpPool = new HpPool(10);

        bar = new HpEnergyBar(energyPool, hpPool);
        HpBarFiller hpFiller = new HpBarFiller(hpPool, utils);
        EnergyBarFiller energyFiller = new EnergyBarFiller(energyPool, utils);
        bar.setFillers(hpFiller, energyFiller);
    }

    @Test
    public void modifyingEnergyPoolIsAppliedOnBar() {

        energyPool.take(40);
        energyPool.update();

        assertEquals(100, bar.getMaxEnergy(), 0);
        assertEquals(62, bar.getEnergy(), 0);
        assertEquals(0.62, bar.getEnergyPercent(), 0.01);
    }

    @Test
    public void modifyinHpPoolIsAppliedOnBar() {

        hpPool.take(40);
        hpPool.update();

        assertEquals(60, bar.getMaxHp(), 0);
        assertEquals(20.4, bar.getHp(), 0.1);
        assertEquals(0.35, bar.getHpPercent(), 0.01);
    }
}
