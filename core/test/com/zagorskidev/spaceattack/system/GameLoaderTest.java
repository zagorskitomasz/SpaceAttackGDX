package com.zagorskidev.spaceattack.system;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.zagorskidev.spaceattack.stages.IStage;

public class GameLoaderTest
{
	private GameLoader loader;

	@Before
	public void setUp()
	{
		loader = Mockito.spy(GameLoader.INSTANCE);
		Mockito.doNothing().when(loader).loadFromFilesystem();
		String fileContent = "{\"mission\" : \"3\", \"level\" : \"5\", \"experience\" : \"999888777666\"}";
		InputStream fileContentStream = new ByteArrayInputStream(fileContent.getBytes());
		Mockito.doReturn(fileContentStream).when(loader).readData();
	}

	@Test
	public void existingFileIsParsedToGameProgress()
	{
		Mockito.doReturn(true).when(loader).isFileExists();

		GameProgress progress = loader.load(Mockito.mock(IStage.class));

		assertEquals(Integer.valueOf(3), progress.getMission());
		assertEquals(Integer.valueOf(5), progress.getLevel());
		assertEquals(Long.valueOf(999888777666l), progress.getExperience());
	}

	@Test
	public void ifFileNotExistsReturnInitialProgress()
	{
		Mockito.doReturn(false).when(loader).isFileExists();

		GameProgress progress = loader.load(Mockito.mock(IStage.class));

		assertEquals(Integer.valueOf(1), progress.getMission());
		assertEquals(Integer.valueOf(1), progress.getLevel());
		assertEquals(Long.valueOf(0l), progress.getExperience());
	}
}
