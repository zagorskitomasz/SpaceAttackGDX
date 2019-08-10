package spaceattack.game.weapons.multiRedLaser;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IActorFactory;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.missiles.FakeLauncher;

public class DoubleRedLaserTest {

    @Mock
    private IWeaponController controller;

    private FakeLauncher launcher;

    @Mock
    private IActorFactory factory;

    private DoubleRedLaser laser;

    @Before
    public void setUp() {

        launcher = new FakeLauncher(null);
        initMocks(this);
        Textures.loadForTest();
        Factories.setVectorFactory(ExtVectorFactory.INSTANCE);
        Factories.setActorFactory(factory);

        laser = new DoubleRedLaser(10);

        laser.setUtils(ExtUtilsFactory.INSTANCE.create());
        laser.setController(controller);
        laser.setMissilesLauncher(launcher);

        doReturn(true).when(controller).takeEnergy(anyFloat());
        doReturn(ExtVectorFactory.INSTANCE.create(100, 100)).when(controller).getPrimaryWeaponUsePlacement();
        doReturn(new FakeActor()).doReturn(new FakeActor()).doReturn(new FakeActor()).when(factory)
                .create(any(IGameActor.class));
    }

    @Test
    public void launchingTwoMissiles() {

        laser.use();
        assertEquals(2, launcher.getLaunched().size());
    }

    @Test
    public void missilesAreMoved() {

        laser.use();

        List<Launchable> launched = launcher.getLaunched();

        assertEquals(75, launched.get(0).getActor().getX(), 0);
        assertEquals(125, launched.get(1).getActor().getX(), 0);
    }
}
