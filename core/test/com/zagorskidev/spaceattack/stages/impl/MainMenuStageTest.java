package com.zagorskidev.spaceattack.stages.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.system.GameLoader;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.ContinueGameButton;
import com.zagorskidev.spaceattack.ui.buttons.ExitGameButton;
import com.zagorskidev.spaceattack.ui.buttons.NewGameButton;

public class MainMenuStageTest
{
	private MainMenuStage stage;

	@Before
	public void setUp()
	{
		stage = Mockito.mock(MainMenuStage.class);
		Mockito.doCallRealMethod().when(stage).init();
		Mockito.doCallRealMethod().when(stage).isCompleted();
		Mockito.doCallRealMethod().when(stage).setResult(ArgumentMatchers.any());
		Mockito.doCallRealMethod().when(stage).loadGame(ArgumentMatchers.any());
		Mockito.doCallRealMethod().when(stage).getGameProgress();
		Mockito.doNothing().when(stage).addActor(ArgumentMatchers.any());
		Mockito.doReturn(Mockito.mock(NewGameButton.class)).when(stage).createNewGameButton();
		Mockito.doReturn(Mockito.mock(ContinueGameButton.class)).when(stage).createContinueGameButton();
		Mockito.doReturn(Mockito.mock(ExitGameButton.class)).when(stage).createExitGameButton();
		stage.init();
	}

	@Test
	public void stageContainsNewGameButton()
	{
		Mockito.verify(stage).addActor(ArgumentMatchers.any(NewGameButton.class));
	}

	@Test
	public void stageContainsContinueGameButton()
	{
		Mockito.verify(stage).addActor(ArgumentMatchers.any(ContinueGameButton.class));
	}

	@Test
	public void stageContainsExitGameButton()
	{
		Mockito.verify(stage).addActor(ArgumentMatchers.any(ExitGameButton.class));
	}

	@Test
	public void afterSettingResultIsCompleted()
	{
		stage.setResult(new StageResult());
		assertTrue(stage.isCompleted());
	}

	@Test
	public void afterLoadingGameProgressIsLoaded()
	{
		GameLoader loader = Mockito.mock(GameLoader.class);
		Mockito.doReturn(Mockito.mock(GameProgress.class)).when(loader).load(stage);

		stage.loadGame(loader);
		assertNotNull(stage.getGameProgress());
	}
}
