package spaceattack.game.weapons.timeWave;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.missiles.Explosion;

public class TimeWaveTest {

    private IVectorFactory factory;

    private TimeWave wave;

    @Mock
    private ITexture texture;

    @Mock
    private IEnemyShip freezable;

    @Mock
    private Explosion notFreezable;

    @Mock
    private Supplier<IVector> positioner;

    @Mock
    private Sounds sound;

    @Mock
    private IAnimation animation;

    private List<IGameActor> actors;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        factory = ExtVectorFactory.INSTANCE;
        Factories.setVectorFactory(factory);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
        Animations.loadForTest();

        wave = new TimeWave();
        wave.setActor(new FakeActor());
        wave.setAnimation(animation);
        wave.setDmg(1000);
        wave.setPositionSupplier(positioner);
        wave.setSound(sound);

        actors = new ArrayList<>();
        actors.add(freezable);
        actors.add(notFreezable);

        doReturn(factory.create(333, 222)).when(positioner).get();
    }

    @Test
    public void waveHitsFreezablesFromDifferentFraction() {

        wave.setPlayersAttack(true);
        wave.setActors(actors);

        wave.launched();

        verify(freezable).freeze(any());
    }

    @Test
    public void waveNotHitsFreezablesFromSameFraction() {

        wave.setPlayersAttack(false);
        wave.setActors(actors);

        wave.launched();

        verify(freezable, never()).freeze(any());
    }

    @Test
    public void soundIsPlayedWhenLaunched() {

        wave.launched();

        verify(sound).play();
    }

    @Test
    public void whenAnimationCompletedWaveIsRemoved() {

        doReturn(true).when(animation).isCompleted();

        wave.act(10);

        assertTrue(wave.isToKill());
    }

    @Test
    public void waveFollowsPositioner() {

        wave.act(10);

        assertEquals(factory.create(333, 222), wave.getPosition());
    }
}
