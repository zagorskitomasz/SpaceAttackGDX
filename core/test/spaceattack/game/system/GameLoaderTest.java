package spaceattack.game.system;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
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

        String fileContent = "{mission:3,level:5,experience:999888777666}";
        InputStream fileContentStream = new ByteArrayInputStream(fileContent.getBytes());

        doReturn(file).when(utils).loadFile(anyString());
        doReturn(fileContentStream).when(file).read();
    }

    @Test
    public void existingFileIsParsedToGameProgress() {

        doReturn(true).when(file).exists();

        GameProgress progress = loader.load();

        assertEquals(Integer.valueOf(3), progress.getMission());
        assertEquals(Integer.valueOf(5), progress.getLevel());
        assertEquals(Long.valueOf(999888777666l), progress.getExperience());
    }

    @Test
    public void ifFileNotExistsReturnInitialProgress() {

        doReturn(false).when(file).exists();

        GameProgress progress = loader.load();

        assertEquals(Integer.valueOf(1), progress.getMission());
        assertEquals(Integer.valueOf(1), progress.getLevel());
        assertEquals(Long.valueOf(0l), progress.getExperience());
    }
}
