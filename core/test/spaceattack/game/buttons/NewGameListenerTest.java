package spaceattack.game.buttons;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.IStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.GameLoader;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.IUtilsFactory;

public class NewGameListenerTest {

    @Mock
    private IGameStage stage;

    @Mock
    private IUtils utils;

    @Mock
    private IUtilsFactory factory;

    @Mock
    private GameLoader loader;

    private NewGameListener listener;

    private StageResult result;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        GameProgress progress = new GameProgress();
        result = new StageResult();
        result.setNextStage(Stages.MISSIONS);
        result.setGameProgress(progress);

        doReturn(progress).when(stage).getGameProgress();
        doReturn(utils).when(factory).create();
        Factories.setUtilsFactory(factory);

        listener = new NewGameListener(stage, loader);
    }

    @Test
    public void ifThereIsNoSaveFileStartGame() {

        doReturn(false).when(loader).fileExists();

        listener.clicked();

        verify(stage).setResult(eq(result));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void ifFileExistsDoConfirm() {

        doReturn(true).when(loader).fileExists();

        listener.clicked();

        verify(utils).confirmDialog(anyString(), anyString(), nullable(IStage.class), any(Consumer.class));
    }

    @Test
    public void confirmedIsSettingResult() {

        listener.processOverrideDialogResult(true);
        verify(stage).setResult(eq(result));
    }

    @Test
    public void notConfirmedIsntSettingResult() {

        listener.processOverrideDialogResult(false);
        verify(stage, times(0)).setResult(any(StageResult.class));
    }
}
