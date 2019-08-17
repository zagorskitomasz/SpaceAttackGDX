package spaceattack.game.weapons.tripleGreenLaser;

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

public class TripleGreenLaserTest {

    @Mock
    private IWeaponController controller;

    private FakeLauncher launcher;

    @Mock
    private IActorFactory factory;

    private TripleGreenLaser tripleGreenLaser;

    @Before
    public void setUp() {

        launcher = new FakeLauncher(null);
        initMocks(this);
        Textures.loadForTest();
        Factories.setVectorFactory(ExtVectorFactory.INSTANCE);
        Factories.setActorFactory(factory);

        tripleGreenLaser = new TripleGreenLaser(10, 0, 0);

        tripleGreenLaser.setUtils(ExtUtilsFactory.INSTANCE.create());
        tripleGreenLaser.setController(controller);
        tripleGreenLaser.setMissilesLauncher(launcher);

        doReturn(true).when(controller).takeEnergy(anyFloat());
        doReturn(false).when(controller).isPlayer();
        doReturn(ExtVectorFactory.INSTANCE.create(100, 100)).when(controller).getSecondaryWeaponUsePlacement();
        doReturn(new FakeActor()).doReturn(new FakeActor()).doReturn(new FakeActor()).when(factory)
                .create(any(IGameActor.class));
    }

    @Test
    public void launchingThreeMissiles() {

        tripleGreenLaser.use();
        assertEquals(3, launcher.getLaunched().size());
    }

    @Test
    public void missilesAreMovedEnemy() {

        tripleGreenLaser.use();

        List<Launchable> launched = launcher.getLaunched();

        assertEquals(50, launched.get(0).getActor().getX(), 0);
        assertEquals(150, launched.get(1).getActor().getX(), 0);
        assertEquals(100, launched.get(2).getActor().getX(), 0);

        assertEquals(80, launched.get(2).getActor().getY(), 0);
    }

    @Test
    public void missilesAreMovedPlayer() {

        doReturn(true).when(controller).isPlayer();

        tripleGreenLaser.use();

        List<Launchable> launched = launcher.getLaunched();

        assertEquals(50, launched.get(0).getActor().getX(), 0);
        assertEquals(150, launched.get(1).getActor().getX(), 0);
        assertEquals(100, launched.get(2).getActor().getX(), 0);

        assertEquals(120, launched.get(2).getActor().getY(), 0);
    }
}
