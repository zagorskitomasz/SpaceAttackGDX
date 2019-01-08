package com.zagorskidev.spaceattack.stages.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.system.GameLoader;
import spaceattack.game.system.graphics.Textures;

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
	public void whenFileNotExistsContinueButtonIsDisabled()
	{
		GameLoader loader = mock(GameLoader.class);
		doReturn(mock(GameProgress.class)).when(loader).load(stage);
		doReturn(false).when(loader).fileExists();

		stage.loadGame(loader);
		verify(continueGameButton).setTouchable(Touchable.disabled);
	}
}
