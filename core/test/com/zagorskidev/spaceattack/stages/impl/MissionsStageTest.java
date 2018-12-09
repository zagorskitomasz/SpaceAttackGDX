package com.zagorskidev.spaceattack.stages.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.zagorskidev.spaceattack.system.GameProgress;

public class MissionsStageTest
{
	private GameProgress progress;
	private MissionsStage stage;

	@Before
	public void setUp()
	{
		progress = new GameProgress();
		stage = mock(MissionsStage.class);
		doCallRealMethod().when(stage).setGameProgress(any(GameProgress.class));
		doCallRealMethod().when(stage).init();
		doCallRealMethod().when(stage).getAct();
		doCallRealMethod().when(stage).nextAct();
		doCallRealMethod().when(stage).previousAct();
		doCallRealMethod().when(stage).calculateMission(anyInt());
		doCallRealMethod().when(stage).setAct(anyInt());
		doCallRealMethod().when(stage).disableMission(anyInt());
		doCallRealMethod().when(stage).showPrevAct();
		doCallRealMethod().when(stage).showNextAct();
	}

	@Test
	public void onFreshGameActIsSetToOne()
	{
		stage.init();
		stage.setGameProgress(progress);

		assertEquals(1, stage.getAct());
	}

	@Test
	public void onThirdMissionActIsSetToOne()
	{
		progress.setMission(3);

		stage.init();
		stage.setGameProgress(progress);

		assertEquals(1, stage.getAct());
	}

	@Test
	public void onFourthMissionActIsSetToTwo()
	{
		progress.setMission(4);

		stage.init();
		stage.setGameProgress(progress);

		assertEquals(2, stage.getAct());
	}

	@Test
	public void onSevenMissionActIsSetToThree()
	{
		progress.setMission(7);

		stage.init();
		stage.setGameProgress(progress);

		assertEquals(3, stage.getAct());
	}

	@Test
	public void onNineMissionActIsSetToThree()
	{
		progress.setMission(9);

		stage.init();
		stage.setGameProgress(progress);

		assertEquals(3, stage.getAct());
	}

	@Test
	public void nextAct()
	{
		progress.setMission(4);

		stage.setGameProgress(progress);
		stage.nextAct();

		assertEquals(3, stage.getAct());
	}

	@Test
	public void previousAct()
	{
		progress.setMission(4);

		stage.init();
		stage.setGameProgress(progress);
		stage.previousAct();

		assertEquals(1, stage.getAct());
	}

	@Test
	public void firstMissionIs1OnFreshGame()
	{
		stage.setAct(1);
		assertEquals(1, stage.calculateMission(1));
	}

	@Test
	public void thirdMissionIs3OnFreshGame()
	{
		stage.setAct(1);
		assertEquals(3, stage.calculateMission(3));
	}

	@Test
	public void firstMissionIs7InThirdAct()
	{
		stage.setAct(3);
		assertEquals(7, stage.calculateMission(1));
	}

	@Test
	public void thirdMissionIs9InThirdAct()
	{
		stage.setAct(3);
		assertEquals(9, stage.calculateMission(3));
	}

	@Test
	public void missionIsEnabledIfLowerThanProgress()
	{
		progress.setMission(3);
		stage.setGameProgress(progress);
		assertFalse(stage.disableMission(2));
	}

	@Test
	public void missionIsEnabledIfEqProgress()
	{
		progress.setMission(5);
		stage.setGameProgress(progress);
		assertFalse(stage.disableMission(2));
	}

	@Test
	public void missionIsDisabledIfHigherThanProgress()
	{
		progress.setMission(5);
		stage.setGameProgress(progress);
		assertTrue(stage.disableMission(3));
	}

	@Test
	public void prevActIsHiddenInFirstAct()
	{
		progress.setMission(2);
		stage.setGameProgress(progress);
		assertFalse(stage.showPrevAct());
	}

	@Test
	public void nextActIsHiddenInThirdAct()
	{
		progress.setMission(9);
		stage.setGameProgress(progress);
		assertFalse(stage.showNextAct());
	}

	@Test
	public void nextActIsHiddenIfMissionInCurrentAct1()
	{
		progress.setMission(5);
		stage.setGameProgress(progress);
		assertFalse(stage.showNextAct());
	}

	@Test
	public void nextActIsHiddenIfMissionInCurrentAct2()
	{
		progress.setMission(5);
		stage.setGameProgress(progress);
		assertFalse(stage.showNextAct());
	}

	@Test
	public void nextActIsVisibleIfMissionAboveCurrentAct()
	{
		progress.setMission(5);
		stage.setGameProgress(progress);
		stage.previousAct();
		assertTrue(stage.showNextAct());
	}
}
