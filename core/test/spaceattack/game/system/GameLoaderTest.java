package spaceattack.game.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.GameProgress;
import spaceattack.game.utils.IUtils;

public class GameLoaderTest {

    private static final String PLAYER_NAME = "PlayerOne";

    private IUtils utils;

    @Mock
    private IFileHandle file;

    private GameLoader loader;

    @Mock
    private GameSaver saver;

    @Before
    public void setUp() {

        utils = spy(ExtUtilsFactory.INSTANCE.create());
        loader = new GameLoader();
        loader.setUtils(utils);
        initMocks(this);
        loader.setSaver(saver);

        String fileContent = "{savedProgress:{class:java.util.HashMap,PlayerOne:{mission:16,level:40,experience:163900,playerName:PlayerOne}}}";
        InputStream fileContentStream = new ByteArrayInputStream(fileContent.getBytes());

        doReturn(file).when(utils).loadFile(anyString());
        doReturn(fileContentStream).when(file).read();
    }

    @Test
    public void ifFileNotExistsReturnNull() {

        doReturn(false).when(file).exists();

        GameProgress progress = loader.load(PLAYER_NAME);

        assertNull(progress);
    }

    @Test
    public void loadingExistingPlayer() {

        doReturn(true).when(file).exists();

        GameProgress progress = loader.load(PLAYER_NAME);

        assertEquals(Integer.valueOf(16), progress.getMission());
        assertEquals(Integer.valueOf(40), progress.getLevel());
        assertEquals(Long.valueOf(163900), progress.getExperience());
        assertEquals(PLAYER_NAME, progress.getPlayerName());
    }

    @Test
    public void ifPlayerNotExistsReturnInitialProgress() {

        doReturn(true).when(file).exists();

        GameProgress progress = loader.load("PlayerTwo");

        assertEquals(Integer.valueOf(1), progress.getMission());
        assertEquals(Integer.valueOf(1), progress.getLevel());
        assertEquals(Long.valueOf(0l), progress.getExperience());
        assertEquals("PlayerTwo", progress.getPlayerName());
    }

    @Test
    public void ifPlayerNotExistsSaveInitialProgress() {

        doReturn(true).when(file).exists();

        GameProgress progress = loader.load("PlayerTwo");

        verify(saver).save(progress);
    }
}
