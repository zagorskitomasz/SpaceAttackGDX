package spaceattack.game.weapons;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.game.actors.IGameActor;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.weapons.missiles.Missile;

public class MissileLauncherTest {

    @Mock
    private IGameStage stage;

    @Mock
    private Missile missile;

    @Mock
    private List<IGameActor> actors;

    private MissilesLauncher launcher;

    @Before
    public void setUp() {

        initMocks(this);
        launcher = new MissilesLauncher(stage);
    }

    @Test
    public void addingActorToStage() {

        launcher.launch(missile);
        verify(stage).addActorBeforeGUI(missile);
    }

    @Test
    public void addingActorsListReferenceToMissile() {

        doReturn(actors).when(stage).getActors();

        launcher.launch(missile);
        verify(missile).setActors(actors);
    }

    @Test
    public void playingSoundAfterShot() {

        launcher.launch(missile);
        verify(missile).launched();
    }
}
