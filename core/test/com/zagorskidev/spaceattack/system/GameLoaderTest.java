package com.zagorskidev.spaceattack.system;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.zagorskidev.spaceattack.stages.IStage;

public class GameLoaderTest
{
	private GameLoader loader;

	@Before
	public void setUp()
	{
		loader = spy(GameLoader.INSTANCE);
		doNothing().when(loader).loadFromFilesystem();
		String fileContent = "{mission:3,level:5,experience:999888777666}";
		InputStream fileContentStream = new ByteArrayInputStream(fileContent.getBytes());
		doReturn(fileContentStream).when(loader).readData();
	}

	@Test
	public void existingFileIsParsedToGameProgress()
	{
		doReturn(true).when(loader).fileExists();

		GameProgress progress = loader.load(mock(IStage.class));

		assertEquals(Integer.valueOf(3), progress.getMission());
		assertEquals(Integer.valueOf(5), progress.getLevel());
		assertEquals(Long.valueOf(999888777666l), progress.getExperience());
	}

	@Test
	public void ifFileNotExistsReturnInitialProgress()
	{
		doReturn(false).when(loader).fileExists();

		GameProgress progress = loader.load(mock(IStage.class));

		assertEquals(Integer.valueOf(1), progress.getMission());
		assertEquals(Integer.valueOf(1), progress.getLevel());
		assertEquals(Long.valueOf(0l), progress.getExperience());
	}
}
