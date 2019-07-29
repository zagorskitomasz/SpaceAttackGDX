package spaceattack.game.buttons;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.GameSaver;

public class CreatePlayerListenerTest {

    private CreatePlayerListener listener;

    @Mock
    private GameSaver saver;

    @Mock
    private IGameStage stage;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        listener = new CreatePlayerListener(2, saver, stage);
    }

    @Test
    public void afterProperInputStageIsCompleted() {

        listener.process("Test");
        verify(stage).setResult(any());
    }

    @Test
    public void newPlayerHasInitialProgressIntoMissionsStage() {

        GameProgress progress = new GameProgress();
        progress.setPlayerName("Test");
        StageResult expected = new StageResult();
        expected.setNextStage(Stages.MISSIONS);
        expected.setGameProgress(progress);

        listener.process("Test");
        verify(stage).setResult(expected);
    }
}
