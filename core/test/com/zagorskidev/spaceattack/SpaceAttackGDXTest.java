package com.zagorskidev.spaceattack;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageFactory;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;

public class SpaceAttackGDXTest
{
	private SpaceAttackGDX game;
	private StageFactory factory;
	private IStage mainMenuStage;
	private IStage missionsStage;

	@Before
	public void setUp() throws Exception
	{
		mockStages();
		mockFactory();
		spyGame();
	}

	void mockStages()
	{
		mainMenuStage = mock(IStage.class);
		when(mainMenuStage.getType()).thenReturn(Stages.MAIN_MENU);
		doNothing().when(mainMenuStage).draw();
		doNothing().when(mainMenuStage).act(any(Float.class));

		missionsStage = mock(IStage.class);
		when(missionsStage.getType()).thenReturn(Stages.MISSIONS);
	}

	void mockFactory()
	{
		factory = mock(StageFactory.class);
		when(factory.getStage(any(StageResult.class))).thenReturn(mainMenuStage);
	}

	void spyGame()
	{
		game = spy(SpaceAttackGDX.class);
		doReturn(factory).when(game).getStageFactory();
		doNothing().when(game).clearScreen();
		doNothing().when(game).setInputProcessor();
		doReturn(30f).when(game).getDeltaTime();
	}

	@Test
	public void mainMenuIsFirstStage()
	{
		game.create();
		assertEquals(Stages.MAIN_MENU, game.getCurrentStage());
	}

	@Test
	public void gameIsLoadedOnMainMenu()
	{
		game.create();
		verify(mainMenuStage).loadGame(any());
	}

	@Test
	public void stageIsSwitchedToTypeReturnedByPreviousStage()
	{
		game.create();

		StageResult result = new StageResult();
		result.setNextStage(Stages.MISSIONS);

		when(mainMenuStage.isCompleted()).thenReturn(true);
		when(mainMenuStage.getResult()).thenReturn(result);
		when(factory.getStage(result)).thenReturn(missionsStage);

		game.render();
		assertEquals(Stages.MISSIONS, game.getCurrentStage());
	}

	@Test
	public void screenIsClearingDuringRender()
	{
		game.create();
		game.render();
		game.render();
		game.render();

		verify(game, times(3)).clearScreen();
	}

	@Test
	public void stageIsActedAndRendered()
	{
		game.create();
		game.render();
		game.render();

		verify(mainMenuStage, times(2)).act(any(Float.class));
		verify(mainMenuStage, times(2)).draw();
	}
}
