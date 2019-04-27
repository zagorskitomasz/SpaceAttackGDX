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

        energyPool = new Pool(50, 10, 5 * Pool.UPDATES_PER_SECOND, 5 * Pool.UPDATES_PER_SECOND);
        hpPool = new Pool(80, 20, 3 * Pool.UPDATES_PER_SECOND, 3 * Pool.UPDATES_PER_SECOND);

        bar = new HpEnergyBar(energyPool, hpPool);
        HpBarFiller hpFiller = new HpBarFiller(hpPool, utils);
        EnergyBarFiller energyFiller = new EnergyBarFiller(energyPool, utils);
        bar.setFillers(hpFiller, energyFiller);
    }

    @Test
    public void modifyingEnergyPoolIsAppliedOnBar() {

        energyPool.setLevel(3);
        energyPool.take(40);
        energyPool.update();

        assertEquals(70, bar.getMaxEnergy(), 0);
        assertEquals(45, bar.getEnergy(), 0);
        assertEquals(0.64, bar.getEnergyPercent(), 0.01);
    }

    @Test
    public void modifyinHpPoolIsAppliedOnBar() {

        hpPool.setLevel(3);
        hpPool.take(40);
        hpPool.update();

        assertEquals(120, bar.getMaxHp(), 0);
        assertEquals(89, bar.getHp(), 0);
        assertEquals(0.74, bar.getHpPercent(), 0.01);
    }
}
