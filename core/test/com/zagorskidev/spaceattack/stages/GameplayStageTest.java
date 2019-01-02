package com.zagorskidev.spaceattack.stages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zagorskidev.spaceattack.input.IInput;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.Ship;
import com.zagorskidev.spaceattack.ships.player.PlayerShip;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.WeaponFactory;

public class GameplayStageTest
{
	private GameplayStage stage;

	@Mock
	private IShip ship;

	@Mock
	private IInput inputProcessor;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		stage = mock(GameplayStage.class);
		doCallRealMethod().when(stage).registerShip(any(IShip.class));
		doCallRealMethod().when(stage).setPrimaryWeapon(any(IWeapon.class));
		doCallRealMethod().when(stage).getPrimaryWeaponUsePlacement();
		doCallRealMethod().when(stage).isGameOver();
		doCallRealMethod().when(stage).lose();
		doCallRealMethod().when(stage).finalizeStage();
		doCallRealMethod().when(stage).getGameProgress();
		doCallRealMethod().when(stage).getProgressBackup();
		doCallRealMethod().when(stage).setWon(anyBoolean());
		doCallRealMethod().when(stage).setGameOver(anyBoolean());
		doCallRealMethod().when(stage).setGameProgress(any(GameProgress.class));
		doCallRealMethod().when(stage).act(0);
		doCallRealMethod().when(stage).notify(any(GameProgress.class));
	}

	@Test
	public void registeringObjectIsInitializingInputProcessor()
	{
		doReturn(inputProcessor).when(stage).initInputProcessor();
		stage.registerShip(ship);

		verify(inputProcessor).registerShip(ship);
	}

	@Test
	public void placementOfLaserShot()
	{
		//@formatter:off
		IWeapon redLaser = WeaponFactory
				.INSTANCE
				.redLaser()
				.setController(stage)
				.setMissileLauncher(null)
				.setLevel(1)
				.build();
		//@formatter:off
		
		doReturn(100f).when(ship).getX();
		doReturn(200f).when(ship).getY();
		doReturn(60f).when(ship).getHeight();
		
		doReturn(inputProcessor).when(stage).initInputProcessor();
		stage.registerShip(ship);
		stage.setPrimaryWeapon(redLaser);
		
		assertEquals(new Vector2(100,242), stage.getPrimaryWeaponUsePlacement());
	}
	
	@Test
	public void removingActorsToKill()
	{
		Array<Actor> actors = new Array<>();
		
		Ship killableToKill = mock(Ship.class);
		Ship killableNotToKill = mock(Ship.class);
		FireButton notKillable = mock(FireButton.class);
		doReturn(true).when(killableToKill).isToKill();
		doReturn(false).when(killableNotToKill).isToKill();
		
		actors.add(killableToKill);
		actors.add(killableNotToKill);
		actors.add(notKillable);
		
		doReturn(actors).when(stage).getActors();
		
		stage.act(0);
		
		assertEquals(2, actors.size);
	}
	
	@Test
	public void killingRequiredActorCausesGameOver()
	{
		Array<Actor> actors = new Array<>();
		
		PlayerShip playersShip = mock(PlayerShip.class);
		doReturn(true).when(playersShip).isToKill();
		actors.add(playersShip);
		doReturn(actors).when(stage).getActors();
		
		stage.act(0);
		
		assertTrue(stage.isGameOver());
	}
	
	@Test
	public void losingGameIsCausingSavingBackupProgress()
	{
		GameProgress baseProgress = spy(new GameProgress());
		GameProgress backupProgress = spy(new GameProgress());

		StageResult result = new StageResult();
		result.setNextStage(Stages.MISSIONS);
		result.setGameProgress(backupProgress);
		
		doNothing().when(baseProgress).notifyObservers();
		
		stage.setGameProgress(baseProgress);
		
		baseProgress.addExperience(1000);
		
		stage.lose();
		stage.finalizeStage();
		
		verify(stage).setResult(eq(result), anyBoolean());
	}
	
	@Test
	public void winningGameIsCausingSaveOfNewProgress()
	{
		GameProgress baseProgress = spy(new GameProgress());

		StageResult result = new StageResult();
		result.setNextStage(Stages.MISSIONS);
		result.setGameProgress(baseProgress);
		
		doNothing().when(baseProgress).notifyObservers();
		
		stage.setGameProgress(baseProgress);
		
		baseProgress.addExperience(1000);
		
		stage.setGameOver(true);
		stage.setWon(true);
		stage.finalizeStage();
		
		verify(stage).setResult(eq(result), anyBoolean());
	}
	
	@Test
	public void levelingUpIsShowingLabel()
	{
		GameProgress progress = new GameProgress();
		stage.setGameProgress(progress);
		progress.setLevel(2);
		
		verify(stage).showLevelUpLabel();
	}
}
