package spaceattack.game.buttons;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.stages.UIStage;

public class ChangeStageButtonListenerTest
{
	@Mock
	private IGameStage stage;

	private ChangeStageButtonListener listener;

	private StageResult result;

	@Before
	public void setUp()
	{
		stage = mock(UIStage.class);
		listener = new ChangeStageButtonListener(stage, Stages.MISSIONS);

		GameProgress progress = new GameProgress();
		result = new StageResult();
		result.setNextStage(Stages.MISSIONS);
		result.setGameProgress(progress);

		doReturn(progress).when(stage).getGameProgress();
	}

	@Test
	public void listenerIsChangingStage()
	{
		listener.clicked();

		verify(stage).setResult(eq(result));
	}
}
