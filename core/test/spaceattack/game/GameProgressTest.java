package spaceattack.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.consts.Experience;
import spaceattack.game.system.notifiers.IObserver;

public class GameProgressTest {

    @Mock
    private IObserver<GameProgress> observer;

    private GameProgress progress;

    @Before
    public void setUp() {

        initMocks(this);
        progress = new GameProgress();
        progress.registerObserver(observer);
    }

    @Test
    public void notifingAboutLevelChange() {

        progress.setLevel(progress.getLevel() + 1);
        verify(observer).notify(progress);
    }

    @Test
    public void addingExperienceOverBreakpointIsIncreasingLevel() {

        progress.addExperience(Experience.INSTANCE.expForLevel(2) + 1);
        verify(observer).notify(progress);
    }

    @Test
    public void addingExperienceOverTwoBreakpointsIsIncreasingTwoLevels() {

        progress.addExperience(Experience.INSTANCE.expForLevel(3) + 1);
        assertEquals(3, progress.getLevel());
    }

    @Test
    public void addingExperienceOverMultipleBreakpointsIsIncreasingMultipleLevels() {

        progress.addExperience(Experience.INSTANCE.expForLevel(5) + 1);
        assertEquals(5, progress.getLevel());
    }

    @Test
    public void equalObjectsHasSameProperties() {

        progress.setExperience(100l);
        progress.setLevel(2);
        progress.setMission(3);

        GameProgress otherProgress = new GameProgress();
        otherProgress.setExperience(100l);
        otherProgress.setLevel(2);
        otherProgress.setMission(3);

        assertEquals(progress, otherProgress);
    }

    @Test
    public void cloningIsCreatingNewObject() {

        GameProgress otherProgress = progress.clone();
        assertFalse(progress == otherProgress);
    }

    @Test
    public void cloningIsCreatingEqualObject() {

        GameProgress otherProgress = progress.clone();
        assertEquals(progress, otherProgress);
    }

    @Test
    public void notifingAboutMissionCompletionUpdatesState() {

        progress.setMission(2);
        progress.missionCompleted(3);

        assertEquals(4, progress.getMission());
    }

    @Test
    public void notifingAboutCompletionOfAlreadyCompletedMissionDoesntChangeState() {

        progress.setMission(4);
        progress.missionCompleted(3);

        assertEquals(4, progress.getMission());
    }
}
