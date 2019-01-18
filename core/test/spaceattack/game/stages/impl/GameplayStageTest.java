package spaceattack.game.stages.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.GameProgress;
import spaceattack.game.actors.TimeLabel;
import spaceattack.game.buttons.weapon.FireButton;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.player.PlayerShip;
import spaceattack.game.stages.IStage;
import spaceattack.game.system.GameLoader;
import spaceattack.game.system.GameSaver;

public class GameplayStageTest
{
	private GameplayStage stage;

	private IStage extStage;

	@Mock
	private GameSaver saver;

	@Mock
	private GameLoader loader;

	@Mock
	private IShip killableToKill;

	@Mock
	private IShip killableNotToKill;

	@Mock
	private FireButton notKillable;

	@Mock
	private PlayerShip playersShip;

	@Mock
	private TimeLabel completedLabel;

	@Mock
	private TimeLabel failedLabel;

	@Mock
	private TimeLabel levelUpLabel;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		extStage = new FakeStage();
		stage = new GameplayStage();
		stage.setStage(extStage);
		stage.setCompletedLabel(completedLabel);
		stage.setFailedLabel(failedLabel);
		stage.setLevelUpLabel(levelUpLabel);
		stage.setGameSaver(saver);
		stage.setGameLoader(loader);

		doReturn(true).when(killableToKill).isToKill();
		doReturn(false).when(killableNotToKill).isToKill();
	}

	@Test
	public void removingActorsToKill()
	{
		stage.addActor(killableToKill);
		stage.addActor(killableNotToKill);
		stage.addActor(notKillable);

		stage.act(0);

		assertEquals(2, stage.getActors().size());
	}

	@Test
	public void killingRequiredActorCausesGameOver()
	{
		doReturn(true).when(playersShip).isToKill();
		stage.addActor(playersShip);

		stage.act(0);

		assertTrue(stage.isGameOver());
	}

	@Test
	public void losingGameIsCausingSavingBackupProgress()
	{
		GameProgress baseProgress = new GameProgress();
		GameProgress backupProgress = new GameProgress();

		stage.setGameProgress(baseProgress);

		baseProgress.addExperience(1000);

		stage.lose();
		stage.finalizeStage();

		verify(saver).save(eq(backupProgress));
	}

	@Test
	public void winningGameIsCausingSaveOfNewProgress()
	{
		GameProgress baseProgress = new GameProgress();

		stage.setGameProgress(baseProgress);

		baseProgress.addExperience(1000);

		stage.setGameOver(true);
		stage.setWon(true);
		stage.finalizeStage();

		verify(saver).save(eq(baseProgress));
	}

	@Test
	public void levelingUpIsShowingLabel()
	{
		GameProgress progress = new GameProgress();
		stage.setGameProgress(progress);
		progress.registerObserver(stage);
		progress.setLevel(2);

		verify(levelUpLabel).show();
	}

	@Test
	public void ifGameOverAndWonCompletedLabelIsShown()
	{
		stage.setGameOver(true);
		stage.setWon(true);
		stage.act(0);

		verify(completedLabel).show();
	}

	@Test
	public void ifGameOverAndLostFailedLabelIsShown()
	{
		stage.setGameOver(true);
		stage.setWon(false);
		stage.act(0);

		verify(failedLabel).show();
	}

	@Test
	public void afterGivenTimeStageIsFinalized()
	{
		GameProgress progress = new GameProgress();
		stage.setGameProgress(progress);
		stage.setGameOver(true);
		stage.setWon(false);

		doReturn(true).when(failedLabel).isVisible();
		stage.act(0);

		doReturn(false).when(failedLabel).isVisible();
		stage.act(0);

		verify(saver).save(eq(progress));
	}
}
