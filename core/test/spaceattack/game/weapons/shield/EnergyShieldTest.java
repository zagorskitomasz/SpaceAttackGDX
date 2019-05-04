package spaceattack.game.weapons.shield;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.player.PlayerShip;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.missiles.ExplosiveMissile;

public class EnergyShieldTest {

    private IVectorFactory vectors;

    @InjectMocks
    private EnergyShield shield;

    @Mock
    private PlayerShip ship;

    @Mock
    private ExplosiveMissile missile;

    @Mock
    private EnergyShield anotherShield;

    @Mock
    private Supplier<IVector> positioner;

    @Mock
    private Predicate<Float> activityChecker;

    @Mock
    private Sounds sound;

    private List<IGameActor> actors;

    @Before
    public void setUp() {

        Animations.loadForTest();
        Sounds.loadForTest();

        shield = new EnergyShield();

        MockitoAnnotations.initMocks(this);
        vectors = ExtVectorFactory.INSTANCE;
        Factories.setVectorFactory(vectors);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

        shield.setActor(new FakeActor());
        shield.setDmg(40);
        shield.setSound(sound);

        actors = new ArrayList<>();
        actors.add(ship);
        actors.add(missile);
        actors.add(anotherShield);

        shield.setActors(actors);

        doReturn(vectors.create(105, 105)).when(ship).getPosition();
        doReturn(vectors.create(95, 105)).when(missile).getPosition();
        doReturn(vectors.create(95, 95)).when(anotherShield).getPosition();
        doReturn(10f).when(ship).getRadius();
        doReturn(10f).when(missile).getRadius();
        doReturn(10f).when(anotherShield).getRadius();

        doReturn(vectors.create(100, 100)).when(positioner).get();
    }

    @Test
    public void whenNoLongerActiveSoundIsStopped() {

        doReturn(false).when(activityChecker).test(anyFloat());

        shield.act(10);

        verify(sound).stop(anyLong());
    }

    @Test
    public void whenNoLongerActiveShieldIsKilled() {

        doReturn(false).when(activityChecker).test(anyFloat());

        shield.act(10);

        assertTrue(shield.isToKill());
    }

    @Test
    public void shieldPositionIsUpdated() {

        doReturn(vectors.create(333, 333)).when(positioner).get();

        shield.act(10);

        assertEquals(vectors.create(333, 333), shield.getPosition());
    }

    @Test
    public void vulnerableShipInRadiusIsDamaged() {

        doReturn(true).when(activityChecker).test(anyFloat());

        shield.act(10);

        verify(ship).takeDmg(2);
    }

    @Test
    public void vulnerableShipOutOfRadiusIsNotDamaged() {

        doReturn(vectors.create(333, 333)).when(positioner).get();
        doReturn(true).when(activityChecker).test(anyFloat());

        shield.act(10);

        verify(ship, never()).takeDmg(anyFloat());
    }

    @Test
    public void missileInRadiusIsDestroyed() {

        doReturn(true).when(activityChecker).test(anyFloat());

        shield.act(10);

        verify(missile).setToKill();
    }

    @Test
    public void anotherShieldInRadiusIsNotDestroyed() {

        doReturn(true).when(activityChecker).test(anyFloat());

        shield.act(10);

        verify(anotherShield, never()).setToKill();
    }
}
