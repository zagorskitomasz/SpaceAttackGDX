package com.zagorskidev.spaceattack.ships;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.zagorskidev.spaceattack.consts.Experience;
import com.zagorskidev.spaceattack.system.GameProgress;

public class ExperiencePoolTest
{
	private static final int LEVEL = 3;
	private static final int LEVEL_BACKUP = 2;

	private ExperiencePool pool;
	private GameProgress progress;
	private GameProgress backup;

	@Before
	public void setUp()
	{
		progress = new GameProgress();
		progress.setLevel(LEVEL);
		progress.setExperience(1700l);

		backup = new GameProgress();
		backup.setLevel(LEVEL_BACKUP);
		backup.setExperience(800l);

		pool = new ExperiencePool(progress, backup);
	}

	@Test
	public void valuesAreGetFromExperienceTable()
	{
		pool.update();

		assertEquals(Experience.nextLevelReq[LEVEL] - Experience.nextLevelReq[LEVEL - 1], pool.getMaxAmount(), 0);
		assertEquals(1700 - Experience.nextLevelReq[LEVEL - 1], pool.getAmount(), 0);
	}

	@Test
	public void afterDestroyingUsingValuesFromBackup()
	{
		pool.update();
		pool.destroy();
		pool.update();

		assertEquals(Experience.nextLevelReq[LEVEL_BACKUP] - Experience.nextLevelReq[LEVEL_BACKUP - 1],
				pool.getMaxAmount(), 0);
		assertEquals(800 - Experience.nextLevelReq[LEVEL_BACKUP - 1], pool.getAmount(), 0);
	}
}
