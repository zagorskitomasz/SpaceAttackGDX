package spaceattack.game.stages;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.system.GameSaver;

public class UIStageTest
{
	@Mock
	private GameSaver saver;

	private UIStage stage;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		stage = new UIStage();
		stage.setGameSaver(saver);
	}

	@Test
	public void afterSettingResultIsCompleted()
	{
		stage.setResult(new StageResult());
		assertTrue(stage.isCompleted());
	}

	@Test
	public void settingResultIsSavingProgressFile()
	{
		GameProgress progress = new GameProgress();
		progress.setExperience(1000l);

		StageResult result = new StageResult();
		result.setGameProgress(progress);

		stage.setGameProgress(new GameProgress());
		stage.setResult(result);

		verify(saver).save(eq(progress));
	}

	@Test
	public void notSavedWhenProgressIsNull()
	{
		StageResult result = new StageResult();

		stage.setResult(result);

		verify(saver, times(0)).save(any(GameProgress.class));
	}

	@Test
	public void notSavedWhenNoChange()
	{
		GameProgress progress = new GameProgress();
		progress.setExperience(1000l);

		StageResult result = new StageResult();
		result.setGameProgress(progress);

		stage.setGameProgress(progress);
		stage.setResult(result);

		verify(saver, times(0)).save(any(GameProgress.class));
	}
}
