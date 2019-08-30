package spaceattack.game.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.powerup.IPowerUpBuilder;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.boss.IFinalBossShipBuilder;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.stages.impl.GameplayStage;

public class Act5Mission15EnemyBaseTest {

    @Mock
    private IFinalBossShipBuilder builder;

    @Mock
    private GameplayStage stage;

    @Mock
    private Radar radar;

    @Mock
    private IPowerUpBuilder powerUpBuilder;

    private Act5Mission15EnemyBase base;

    private IBoss spaceStationI;
    private IBoss spaceStationII;
    private IBoss spaceStationIII;

    private IBoss helperI;
    private IBoss helperII;

    @Before
    public void setUp() {

        Factories.setVectorFactory(ExtVectorFactory.INSTANCE);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

        MockitoAnnotations.initMocks(this);
        base = new Act5Mission15EnemyBase(ExtUtilsFactory.INSTANCE.create(), builder);
        base.setStage(stage);
        base.setRadar(radar);
        base.setPowerUpBuilder(powerUpBuilder);

        spaceStationI = new FakeShip();
        spaceStationI.setHpPool(new HpPool(15));
        doReturn(spaceStationI).when(builder).createSpaceStationI(stage);

        helperI = new FakeShip();
        helperI.setHpPool(new HpPool(15));
        doReturn(helperI).when(builder).createHelperI(stage);

        spaceStationII = new FakeShip();
        spaceStationII.setHpPool(new HpPool(15));
        doReturn(spaceStationII).when(builder).createSpaceStationII(stage);

        helperII = new FakeShip();
        helperII.setHpPool(new HpPool(15));
        doReturn(helperII).when(builder).createHelperII(stage);

        spaceStationIII = new FakeShip();
        spaceStationIII.setHpPool(new HpPool(15));
        doReturn(spaceStationIII).when(builder).createSpaceStationIII(stage);
    }

    @Test
    public void firstShipIsSpaceStationI() {

        base.act(0);

        verify(builder).createSpaceStationI(stage);
    }

    @Test
    public void spaceStationIisSendHomeWhenHpBelowGivenPercent() {

        base.act(0);
        spaceStationI.takeDmg(50);
        base.act(0);

        assertEquals(360, spaceStationI.getX(), 0);
        assertEquals(4280, spaceStationI.getY(), 0);
    }

    @Test
    public void afterSendingHomeSpaceStationIisImmortal() {

        base.act(0);
        spaceStationI.takeDmg(50);
        base.act(0);
        spaceStationI.takeDmg(40);

        assertEquals(85, spaceStationI.getHpPool().getAmount(), 0);
    }

    @Test
    public void afterKillingStationIHeplerIisInvoked() {

        base.act(0);
        spaceStationI.takeDmg(50);
        base.act(0);
        base.act(0);
        verify(builder).createHelperI(stage);
    }

    @Test
    public void afterKillingHeplerIStationIIisInvoked() {

        base.act(0);
        spaceStationI.takeDmg(50);
        base.act(0);
        base.act(0);
        helperI.setToKill();
        base.act(0);
        base.act(0);
        verify(builder).createSpaceStationII(stage);
    }

    @Test
    public void spaceStationIIisSendHomeWhenHpBelowGivenPercent() {

        base.act(0);
        spaceStationI.takeDmg(100);
        base.act(0);
        base.act(0);
        helperI.setToKill();
        base.act(0);
        base.act(0);
        spaceStationII.takeDmg(150);
        base.act(0);

        assertEquals(360, spaceStationII.getX(), 0);
        assertEquals(4280, spaceStationII.getY(), 0);
    }

    @Test
    public void afterSendingHomeSpaceStationIIisImmortal() {

        base.act(0);
        spaceStationI.takeDmg(50);
        base.act(0);
        base.act(0);
        helperI.setToKill();
        base.act(0);
        base.act(0);
        spaceStationII.takeDmg(70);
        base.act(0);
        spaceStationII.takeDmg(10);

        assertEquals(55, spaceStationII.getHpPool().getAmount(), 0);
    }

    @Test
    public void afterKillingStationIIHeplerIIisInvoked() {

        base.act(0);
        spaceStationI.takeDmg(100);
        base.act(0);
        base.act(0);
        helperI.setToKill();
        base.act(0);
        base.act(0);
        spaceStationII.takeDmg(150);
        base.act(0);
        base.act(0);
        verify(builder).createHelperII(stage);
    }

    @Test
    public void afterKillingHeplerIIStationIIIisInvoked() {

        base.act(0);
        spaceStationI.takeDmg(100);
        base.act(0);
        base.act(0);
        helperI.setToKill();
        base.act(0);
        base.act(0);
        spaceStationII.takeDmg(150);
        base.act(0);
        base.act(0);
        helperII.setToKill();
        base.act(0);
        base.act(0);
        verify(builder).createSpaceStationIII(stage);
    }

    @Test
    public void spaceStationIIIcanBeKilled() {

        base.act(0);
        spaceStationI.takeDmg(50);
        base.act(0);
        base.act(0);
        helperI.setToKill();
        base.act(0);
        base.act(0);
        spaceStationII.takeDmg(70);
        base.act(0);
        base.act(0);
        helperII.setToKill();
        base.act(0);
        base.act(0);
        spaceStationIII.takeDmg(220);

        assertTrue(spaceStationIII.getHpPool().getAmount() <= 0);
    }
}
