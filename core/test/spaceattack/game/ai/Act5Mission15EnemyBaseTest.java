package spaceattack.game.ai;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.boss.IFinalBossShipBuilder;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.impl.GameplayStage;

public class Act5Mission15EnemyBaseTest {

    @Mock
    private IFinalBossShipBuilder builder;

    @Mock
    private GameplayStage stage;

    private Act5Mission15EnemyBase base;

    private IBoss spaceStationI;
    private IPool hpPoolSpaceStationI;

    @Before
    public void setUp() {

        Factories.setVectorFactory(ExtVectorFactory.INSTANCE);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

        MockitoAnnotations.initMocks(this);
        base = new Act5Mission15EnemyBase(ExtUtilsFactory.INSTANCE.create(), builder);
        base.setStage(stage);

        spaceStationI = new FakeShip();
        hpPoolSpaceStationI = new HpPool(100, 0, 0, 0);
        spaceStationI.setHpPool(hpPoolSpaceStationI);
        doReturn(spaceStationI).when(builder).createSpaceStationI(stage);
    }

    @Test
    public void firstShipIsSpaceStationI() {

        base.act(0);

        verify(builder).createSpaceStationI(stage);
    }

    @Test
    public void spaceStationIIsSendHomeWhenHpBelowGivenPercent() {

        base.act(0);
        spaceStationI.takeDmg(50);
        base.act(0);

        assertEquals(360, spaceStationI.getX(), 0);
        assertEquals(4280, spaceStationI.getY(), 0);
    }

    @Test
    public void afterSendingHomeSpaceStationIIsImmortal() {

        base.act(0);
        spaceStationI.takeDmg(50);
        base.act(0);
        spaceStationI.takeDmg(40);

        assertEquals(50, spaceStationI.getHpPool().getAmount(), 0);
    }
}
