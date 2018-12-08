package com.zagorskidev.spaceattack.ui.buttons;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.impl.MainMenuStage;
import com.zagorskidev.spaceattack.system.GameProgress;

public class ContinueGameButtonTest
{
	private MainMenuStage stage;
	private ChangeStageButtonListener listener;
	private ContinueGameButton button;

	private StageResult result;

	@Before
	public void setUp()
	{
		stage = mock(MainMenuStage.class);
		button = mock(ContinueGameButton.class);
		listener = spy(new ChangeStageButtonListener(stage, Stages.MISSIONS));

		doCallRealMethod().when(button).setStage(stage);
		doCallRealMethod().when(stage).getGameProgress();
		button.setStage(stage);

		GameProgress progress = new GameProgress();
		progress.setExperience(0l);
		progress.setLevel(1);
		progress.setMission(1);

		result = new StageResult();
		result.setNextStage(Stages.MISSIONS);
		result.setGameProgress(progress);
	}

	@Test
	public void StartGame()
	{
		listener.clicked(null, 0, 0);

		verify(stage).setResult(eq(result), eq(true));
	}
}
