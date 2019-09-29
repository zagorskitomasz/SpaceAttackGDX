package spaceattack.game.buttons;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.stages.Stages;
import spaceattack.game.stages.UIStage;
import spaceattack.game.system.GameSaver;

public class CreatePlayerListenerTest {

    private CreatePlayerListener listener;

    @Mock
    private GameSaver saver;

    @Mock
    private UIStage stage;

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
    public void newPlayerHasInitialProgressIntoMainMenuStage() {

        GameProgress progress = new GameProgress();
        progress.setPlayerName("Test");
        progress.setSlot(2);
        StageResult expected = new StageResult();
        expected.setNextStage(Stages.MAIN_MENU);
        expected.setGameProgress(progress);

        listener.process("Test");
        verify(stage).setResult(expected);
    }

    @Test
    public void nameCantCointainSpaces() {

        assertFalse(listener.validate(" test"));
    }

    @Test
    public void nameCantCointainSpecialChars() {

        assertFalse(listener.validate("te$#st"));
    }

    @Test
    public void nameCantBeMoreThan10Chars() {

        assertFalse(listener.validate("testtesttest"));
    }

    @Test
    public void nameHasLettersAndNumbers() {

        assertTrue(listener.validate("test1234"));
    }
}
