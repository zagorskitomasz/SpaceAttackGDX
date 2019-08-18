package spaceattack.game.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.GameProgress;
import spaceattack.game.rpg.Attribute;
import spaceattack.game.rpg.Improvement;
import spaceattack.game.utils.IUtils;

public class GameLoaderTest {

    private static final String PLAYER_NAME = "PlayerOne";
    private static final String SLOT_INDEX = "2";

    private IUtils utils;

    @Mock
    private IFileHandle file;

    private GameLoader loader;

    @Before
    public void setUp() {

        utils = spy(ExtUtilsFactory.INSTANCE.create());
        loader = new GameLoader();
        loader.setUtils(utils);
        initMocks(this);

        String fileContent = "{savedProgress:{2:{mission:16,level:40,experience:163900,playerName:PlayerOne,attributes:{attributes:{ENGINE:8,ARMORY:10,SHIELDS:8,BATTERY:9},freePoints:2},improvements:{improvements:{SNIPER:0,FEAR:0,ABSORBER:3,RED_LASER_MASTERY:0,REGENERATION:0,GREEN_LASER_MASTERY:0,AMMO_COLLECTOR:0,SPRINTER:0,ADRENALINE:1,BERSERKER:0},freePoints:1}}}}";
        InputStream fileContentStream = new ByteArrayInputStream(fileContent.getBytes());

        doReturn(file).when(utils).loadFile(anyString());
        doReturn(fileContentStream).when(file).read();
    }

    @Test
    public void ifFileNotExistsReturnNull() {

        doReturn(false).when(file).exists();

        GameProgress progress = loader.load(SLOT_INDEX);

        assertNull(progress);
    }

    @Test
    public void loadingExistingPlayer() {

        doReturn(true).when(file).exists();

        GameProgress progress = loader.load(SLOT_INDEX);

        assertEquals(16, progress.getMission());
        assertEquals(40, progress.getLevel());
        assertEquals(163900, progress.getExperience());
        assertEquals(PLAYER_NAME, progress.getPlayerName());
    }

    @Test
    public void ifPlayerNotExistsReturnNull() {

        doReturn(true).when(file).exists();

        GameProgress progress = loader.load("4");

        assertNull(progress);
    }

    @Test
    public void loadingPlayersNames() {

        doReturn(true).when(file).exists();

        Map<String, String> allPlayers = loader.loadAll();

        assertEquals(PLAYER_NAME, allPlayers.get(SLOT_INDEX));
    }

    @Test
    public void loadingAttributes() {

        doReturn(true).when(file).exists();

        GameProgress progress = loader.load(SLOT_INDEX);

        assertEquals(2, progress.getAttributes().getFreePoints());
        assertEquals(10, progress.getAttributes().get(Attribute.ARMORY));
        assertEquals(9, progress.getAttributes().get(Attribute.BATTERY));
        assertEquals(8, progress.getAttributes().get(Attribute.ENGINE));
        assertEquals(8, progress.getAttributes().get(Attribute.SHIELDS));
    }

    @Test
    public void loadingImprovements() {

        doReturn(true).when(file).exists();

        GameProgress progress = loader.load(SLOT_INDEX);

        assertEquals(1, progress.getImprovements().getFreePoints());
        assertEquals(3, progress.getImprovements().get(Improvement.ABSORBER));
        assertEquals(1, progress.getImprovements().get(Improvement.ADRENALINE));
        assertEquals(0, progress.getImprovements().get(Improvement.AMMO_COLLECTOR));
        assertEquals(0, progress.getImprovements().get(Improvement.FEAR));
    }
}
