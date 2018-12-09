package com.zagorskidev.spaceattack.ui.buttons;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameProgress;

public class ChangeStageButtonListenerTest
{
	private UIStage stage;
	private ChangeStageButtonListener listener;
	private StageResult result;

	@Before
	public void setUp()
	{
		stage = mock(UIStage.class);
		listener = spy(new ChangeStageButtonListener(stage, Stages.MISSIONS));

		GameProgress progress = new GameProgress();
		progress.setExperience(100l);
		progress.setLevel(2);
		progress.setMission(3);

		result = new StageResult();
		result.setNextStage(Stages.MISSIONS);
		result.setGameProgress(progress);

		doReturn(progress).when(stage).getGameProgress();
	}

	@Test
	public void listenerIsChangingStage()
	{
		listener.clicked(null, 0, 0);

		verify(stage).setResult(eq(result), eq(true));
	}
}
