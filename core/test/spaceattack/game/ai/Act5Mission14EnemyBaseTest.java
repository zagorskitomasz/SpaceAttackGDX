package spaceattack.game.ai;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.actor.ExtActorFactory;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.enemy.IEnemyShipsFactory;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.Acts;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.greenLaser.GreenLaser;
import spaceattack.game.weapons.shield.EnergyShieldEmiter;

public class Act5Mission14EnemyBaseTest {

    private Act5Mission14EnemyBase base;

    @Mock
    private IPool hpPool;

    @Mock
    private GameplayStage stage;

    @Mock
    private IEnemyShip superChaserLeft;

    @Mock
    private IEnemyShip superChaserRight;

    @Mock
    private Radar radar;

    @Mock
    private IEnemyShipsFactory factory;

    @Mock
    private IEnemyShip directMover;

    @Mock
    private IEnemyShip leftMover;

    @Mock
    private IBoss boss;

    @Mock
    private EnergyShieldEmiter shieldEmitter;

    @Mock
    private GreenLaser greenLaser;

    @Mock
    private IWeaponController controller;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        Factories.setActorFactory(ExtActorFactory.INSTANCE);
        Factories.setVectorFactory(ExtVectorFactory.INSTANCE);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
        Textures.loadForTest();

        doReturn(superChaserLeft).doReturn(superChaserRight).when(factory).createSuperChaser(Acts.V, stage);
        doReturn(MoverType.CENTRAL_STATION).when(boss).getDefaultMoverType();
        doReturn(ShooterType.INSTANT_SHOOTER).when(boss).getDefaultShooterType();
        doReturn(hpPool).when(superChaserLeft).getHpPool();
        doReturn(hpPool).when(superChaserRight).getHpPool();
        doReturn(controller).when(boss).getWeaponController();
        doReturn(shieldEmitter).when(controller).getPrimaryWeapon();

        base = new Act5Mission14EnemyBase(ExtUtilsFactory.INSTANCE.create());
        base.setActor(new FakeActor());
        base.setRadar(radar);
        base.setShipsFactory(factory);
        base.setStage(stage);
        base.setBoss(boss);
    }

    @Test
    public void superChasersAreAddedWithBoss() {

        base.addBoss();

        verify(stage).addActorBeforeGUI(superChaserLeft);
        verify(stage).addActorBeforeGUI(superChaserRight);
        verify(stage).addActorBeforeGUI(boss);
    }

    @Test
    public void whenPrimaryWeaponWillChangeAppWontCrash() {

        doReturn(greenLaser).when(controller).getPrimaryWeapon();

        base.addBoss();

        verify(stage).addActorBeforeGUI(superChaserLeft);
        verify(stage).addActorBeforeGUI(superChaserRight);
        verify(stage).addActorBeforeGUI(boss);
    }

    @Test
    public void withBothHelpersAliveShieldIsUp() {

        doReturn(false).when(superChaserLeft).isToKill();
        doReturn(false).when(superChaserRight).isToKill();

        doAnswer(invocation -> {
            Predicate<Float> predicate = invocation.getArgument(0);
            assertTrue(predicate.test(0f));
            return null;
        }).when(shieldEmitter).setActivityPredicate(any());

        base.addBoss();

        verify(shieldEmitter).setActivityPredicate(any());
    }

    @Test
    public void withOneHelperAliveShieldIsUp() {

        doReturn(true).when(superChaserLeft).isToKill();
        doReturn(false).when(superChaserRight).isToKill();

        doAnswer(invocation -> {
            Predicate<Float> predicate = invocation.getArgument(0);
            assertTrue(predicate.test(0f));
            return null;
        }).when(shieldEmitter).setActivityPredicate(any());

        base.addBoss();

        verify(shieldEmitter).setActivityPredicate(any());
    }

    @Test
    public void withNoHelperAliveShieldIsDown() {

        doReturn(true).when(superChaserLeft).isToKill();
        doReturn(true).when(superChaserRight).isToKill();

        doAnswer(invocation -> {
            Predicate<Float> predicate = invocation.getArgument(0);
            assertFalse(predicate.test(0f));
            return null;
        }).when(shieldEmitter).setActivityPredicate(any());

        base.addBoss();

        verify(shieldEmitter).setActivityPredicate(any());
    }
}
