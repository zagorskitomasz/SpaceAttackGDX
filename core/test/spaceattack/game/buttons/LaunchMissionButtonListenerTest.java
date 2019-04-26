package spaceattack.game.buttons;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.stages.Stages;
import spaceattack.game.stages.impl.MissionsStage;

public class LaunchMissionButtonListenerTest {

    private static final int GRID_POSITION = 1;

    @Mock
    private MissionsStage stage;

    private LaunchMissionButtonListener listener;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        listener = new LaunchMissionButtonListener(stage, GRID_POSITION);
    }

    @Test
    public void launchingProperStage() {

        GameProgress progress = new GameProgress();
        StageResult result = new StageResult();
        result.setGameProgress(progress);
        result.setNextStage(Stages.MISSION_2);

        doReturn(progress).when(stage).getGameProgress();
        doReturn(2).when(stage).calculateMission(GRID_POSITION);

        listener.clicked();

        verify(stage).setResult(result);
    }
}
