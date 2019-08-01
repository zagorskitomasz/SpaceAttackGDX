package spaceattack.game.stages.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.buttons.IButton;
import spaceattack.game.system.GameLoader;
import spaceattack.game.system.GameSaver;

public class MainMenuStageTest {

    @Mock
    private GameSaver saver;

    @Mock
    private GameLoader loader;

    @Mock
    private IButton button;

    private MainMenuStage stage;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        stage = new MainMenuStage();
        stage.setGameSaver(saver);
        stage.setGameLoader(loader);
    }

    @Test
    public void afterSettingResultIsCompleted() {

        stage.setResult(new StageResult());
        assertTrue(stage.isCompleted());
    }

    @Test
    public void settingResultIsSavingProgressFile() {

        doReturn(true).when(loader).fileExists();

        GameProgress progress = new GameProgress();
        progress.setExperience(1000l);

        StageResult result = new StageResult();
        result.setGameProgress(progress);

        stage.setGameProgress(new GameProgress());
        stage.setResult(result);

        verify(saver).save(eq(progress));
    }

    @Test
    public void notSavedWhenProgressIsNull() {

        doReturn(true).when(loader).fileExists();

        StageResult result = new StageResult();

        stage.setResult(result);

        verify(saver, times(0)).save(any(GameProgress.class));
    }

    @Test
    public void notSavedWhenNoChange() {

        doReturn(true).when(loader).fileExists();

        GameProgress progress = new GameProgress();
        progress.setExperience(1000l);

        StageResult result = new StageResult();
        result.setGameProgress(progress);

        stage.setGameProgress(progress);
        stage.setResult(result);

        verify(saver, times(0)).save(any(GameProgress.class));
    }

    @Test
    public void forceSaveWhenFileNotExists() {

        doReturn(false).when(loader).fileExists();

        GameProgress progress = new GameProgress();
        progress.setExperience(1000l);

        StageResult result = new StageResult();
        result.setGameProgress(progress);

        stage.setGameProgress(progress);
        stage.setResult(result);

        verify(saver).save(eq(progress));
    }
}
