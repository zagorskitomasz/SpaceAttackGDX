package com.zagorskidev.spaceattack.ui.buttons;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.impl.MainMenuStage;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.NewGameButton.ConfirmNewGameDialog;
import com.zagorskidev.spaceattack.ui.buttons.NewGameButton.NewGameListener;

public class NewGameButtonTest
{
	private MainMenuStage stage;
	private ConfirmNewGameDialog dialog;
	private NewGameListener listener;
	private NewGameButton button;

	private StageResult result;

	@Before
	public void setUp()
	{
		stage = mock(MainMenuStage.class);
		button = mock(NewGameButton.class);
		listener = spy(button.new NewGameListener());
		dialog = mock(ConfirmNewGameDialog.class);

		doCallRealMethod().when(button).setStage(stage);
		button.setStage(stage);

		doCallRealMethod().when(dialog).setListener(any());
		doCallRealMethod().when(dialog).result(any());
		dialog.setListener(listener);
		doNothing().when(listener).confirm();

		GameProgress progress = new GameProgress();
		progress.setExperience(0l);
		progress.setLevel(1);
		progress.setMission(1);

		result = new StageResult();
		result.setNextStage(Stages.MISSIONS);
		result.setGameProgress(progress);
	}

	@Test
	public void ifThereIsNoSaveFileStartGame()
	{
		doReturn(false).when(listener).isSaveFileExists();
		listener.clicked(null, 0, 0);

		verify(stage).setResult(eq(result), eq(true));
	}

	@Test
	public void ifFileExistsDoConfirm()
	{
		doReturn(true).when(listener).isSaveFileExists();

		listener.clicked(null, 0, 0);

	}

	@Test
	public void confirmedIsSettingResult()
	{
		dialog.result(true);

		verify(stage).setResult(eq(result), eq(true));
	}

	@Test
	public void notConfirmedIsntSettingResult()
	{
		dialog.result(false);

		verify(stage, times(0)).setResult(eq(result), eq(true));
	}
}
