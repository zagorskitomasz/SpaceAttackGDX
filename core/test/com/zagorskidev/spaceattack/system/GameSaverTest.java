package com.zagorskidev.spaceattack.system;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GameSaverTest
{
	private GameSaver saver;
	private String fileContent;

	@Before
	public void setUp()
	{
		saver = spy(GameSaver.INSTANCE);
		doNothing().when(saver).loadFromFilesystem();
		doNothing().when(saver).writeToFile(any());
		fileContent = "{mission:3,level:5,experience:999888777666}";
	}

	@Test
	public void gameProgressIsParsedProperly()
	{
		GameProgress progress = new GameProgress();
		progress.setExperience(999888777666l);
		progress.setLevel(5);
		progress.setMission(3);

		saver.save(progress);
		Mockito.verify(saver).writeToFile(fileContent);
	}
}
