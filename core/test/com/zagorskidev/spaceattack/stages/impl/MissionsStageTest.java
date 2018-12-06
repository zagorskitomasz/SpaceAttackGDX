package com.zagorskidev.spaceattack.stages.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zagorskidev.spaceattack.system.GameProgress;

public class MissionsStageTest
{
	private GameProgress progress;
	private MissionsStage stage;

	@Before
	public void setUp()
	{
		progress = new GameProgress();
		stage = Mockito.mock(MissionsStage.class);
		Mockito.doCallRealMethod().when(stage).setGameProgress(ArgumentMatchers.any(GameProgress.class));
		Mockito.doCallRealMethod().when(stage).init();
		Mockito.doCallRealMethod().when(stage).getAct();
		Mockito.doCallRealMethod().when(stage).nextAct();
		Mockito.doCallRealMethod().when(stage).previousAct();
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
}
