package com.zagorskidev.spaceattack.stages;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StagesTest
{
	@Test
	public void getByNameIsReturningProperMission()
	{
		assertEquals(Stages.MISSION_2, Stages.getMissionStage(2));
	}
}
