package com.zagorskidev.spaceattack.stages.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.system.GameProgress;

import spaceattack.game.system.GameLoader;
import spaceattack.game.system.GameSaver;

public class MainMenuStageTest
{
	private MainMenuStage stage;
	private Button continueGameButton;
	private GameProgress progress;

	@Before
	public void setUp()
	{
		Textures.loadForTest();
		stage = mock(MainMenuStage.class);
		doCallRealMethod().when(stage).init();
		doCallRealMethod().when(stage).isCompleted();
		doCallRealMethod().when(stage).setResult(any(StageResult.class), anyBoolean());
		doCallRealMethod().when(stage).loadGame(any());
		doCallRealMethod().when(stage).getGameProgress();
		doCallRealMethod().when(stage).setGameProgress(any());
		doNothing().when(stage).addActor(any());

		continueGameButton = mock(TextButton.class);

		doReturn(mock(TextButton.class)).when(stage).createNewGameButton();
		doReturn(continueGameButton).when(stage).createContinueGameButton();
		doReturn(mock(TextButton.class)).when(stage).createExitGameButton();
		stage.init();

		progress = new GameProgress();
		progress.setMission(4);
		progress.setLevel(6);
		progress.setExperience(1000l);
	}

	@Test
	public void stageContainsThreeButton()
	{
		verify(stage, times(3)).addActor(any(TextButton.class));
	}

	@Test
	public void afterSettingResultIsCompleted()
	{
		stage.setResult(new StageResult(), false);
		assertTrue(stage.isCompleted());
	}

	@Test
	public void afterLoadingGameProgressIsLoaded()
	{
		GameLoader loader = mock(GameLoader.class);
		doReturn(mock(GameProgress.class)).when(loader).load(stage);

		stage.loadGame(loader);
		assertNotNull(stage.getGameProgress());
	}

	@Test
	public void whenFileNotExistsContinueButtonIsDisabled()
	{
		GameLoader loader = mock(GameLoader.class);
		doReturn(mock(GameProgress.class)).when(loader).load(stage);
		doReturn(false).when(loader).fileExists();

		stage.loadGame(loader);
		verify(continueGameButton).setTouchable(Touchable.disabled);
	}

	@Test
	public void settingResultIsSavingProgressFile()
	{
		StageResult result = new StageResult();
		result.setGameProgress(progress);

		stage.setResult(result, false);

		verify(stage).saveProgress(any(GameSaver.class), any(GameProgress.class));
	}

	@Test
	public void notSavedWhenProgressIsNull()
	{
		StageResult result = new StageResult();

		stage.setResult(result, false);

		verify(stage, times(0)).saveProgress(any(GameSaver.class), any(GameProgress.class));
	}

	@Test
	public void notSavedWhenNoChange()
	{
		StageResult result = new StageResult();
		result.setGameProgress(progress);

		stage.setGameProgress(progress);
		stage.setResult(result, false);

		verify(stage, times(0)).saveProgress(any(GameSaver.class), any(GameProgress.class));
	}
}
