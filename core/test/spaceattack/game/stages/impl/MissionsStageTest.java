package spaceattack.game.stages.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.GameProgress;
import spaceattack.game.buttons.IButton;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.Textures;

public class MissionsStageTest {

    @Mock
    private StaticImage actLogo;

    @Mock
    private IButton button;

    private MissionsStage stage;

    private GameProgress progress;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        stage = new MissionsStage();
        progress = new GameProgress();
        stage.addActLogoImage(actLogo);
        stage.setAct(1);

        Textures.loadForTest();
    }

    @Test
    public void firstMissionIs0OnFreshGame() {

        stage.setAct(1);

        assertEquals(1, stage.calculateMission(0));
    }

    @Test
    public void thirdMissionIs3OnFreshGame() {

        stage.setAct(1);

        assertEquals(3, stage.calculateMission(2));
    }

    @Test
    public void firstMissionIs7InThirdAct() {

        stage.setAct(3);

        assertEquals(7, stage.calculateMission(0));
    }

    @Test
    public void thirdMissionIs9InThirdAct() {

        stage.setAct(3);

        assertEquals(9, stage.calculateMission(2));
    }

    @Test
    public void onFreshGameActIsSetToOne() {

        stage.setGameProgress(progress);

        assertEquals(1, stage.getAct());
    }

    @Test
    public void onThirdMissionActIsSetToOne() {

        progress.setMission(3);
        stage.setGameProgress(progress);

        assertEquals(1, stage.getAct());
    }

    @Test
    public void onFourthMissionActIsSetToTwo() {

        progress.setMission(4);
        stage.setGameProgress(progress);

        assertEquals(2, stage.getAct());
    }

    @Test
    public void onSevenMissionActIsSetToThree() {

        progress.setMission(7);
        stage.setGameProgress(progress);

        assertEquals(3, stage.getAct());
    }

    @Test
    public void onNineMissionActIsSetToThree() {

        progress.setMission(9);
        stage.setGameProgress(progress);

        assertEquals(3, stage.getAct());
    }

    @Test
    public void nextAct() {

        progress.setMission(4);
        stage.setGameProgress(progress);
        stage.nextAct();

        assertEquals(3, stage.getAct());
    }

    @Test
    public void previousAct() {

        progress.setMission(4);
        stage.setGameProgress(progress);
        stage.previousAct();

        assertEquals(1, stage.getAct());
    }

    @Test
    public void missionIsEnabledIfLowerThanProgress() {

        progress.setMission(3);
        stage.setGameProgress(progress);

        doReturn(1).when(button).getGridPosition();

        assertTrue(stage.isMissionButtonEnabled(button));
    }

    @Test
    public void missionIsEnabledIfEqProgress() {

        progress.setMission(5);
        stage.setGameProgress(progress);

        doReturn(1).when(button).getGridPosition();

        assertTrue(stage.isMissionButtonEnabled(button));
    }

    @Test
    public void missionIsDisabledIfHigherThanProgress() {

        progress.setMission(5);
        stage.setGameProgress(progress);

        doReturn(2).when(button).getGridPosition();

        assertFalse(stage.isMissionButtonEnabled(button));
    }

    @Test
    public void stageIsUpdatingButtonsEnabled() {

        stage.addButtonsEnabledPredicate(button, button -> true);

        stage.updateControls();

        verify(button).setEnabled(true);
    }

    @Test
    public void stageIsUpdatingButtonsVisible() {

        stage.addButtonsVisiblePredicate(button, button -> true);

        stage.updateControls();

        verify(button).setVisible(true);
    }

    @Test
    public void stageIsUpdatingButtonsText() {

        String test = "Test";
        stage.addButtonsTextFunction(button, button -> test);

        stage.updateControls();

        verify(button).setText(test);
    }

    @Test
    public void actLogoImageIsUpdated() {

        stage.setAct(3);

        stage.updateControls();

        verify(actLogo).setTexture(any());
    }

    @Test
    public void prevActIsHiddenInFirstAct() {

        progress.setMission(2);

        stage.setGameProgress(progress);

        assertFalse(stage.isPreviousActButtonVisible(button));
    }

    @Test
    public void nextActIsHiddenInThirdAct() {

        progress.setMission(9);
        stage.setGameProgress(progress);
        assertFalse(stage.isNextActButtonVisible(button));
    }

    @Test
    public void nextActIsHiddenIfMissionInCurrentAct1() {

        progress.setMission(5);
        stage.setGameProgress(progress);
        assertFalse(stage.isNextActButtonVisible(button));
    }

    @Test
    public void nextActIsHiddenIfMissionInCurrentAct2() {

        progress.setMission(5);
        stage.setGameProgress(progress);
        assertFalse(stage.isNextActButtonVisible(button));
    }

    @Test
    public void nextActIsVisibleIfMissionAboveCurrentAct() {

        progress.setMission(5);
        stage.setGameProgress(progress);
        stage.previousAct();
        assertTrue(stage.isNextActButtonVisible(button));
    }
}
