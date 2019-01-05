package spaceattack.game.main;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.system.GameLoader;

import spaceattack.game.stages.StageBuilder;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.ExtUtils;

public class GameTest
{
	@Mock
	private ExtUtils extUtils;

	@Mock
	private GameLoader gameLoader;

	@Mock
	private StageBuilder stageBuilder;

	@Mock
	private FrameController frameController;

	@Mock
	private IStage stage;

	@Mock
	private IStage missionsStage;

	@InjectMocks
	private Game game;

	@Before
	public void setUp()
	{
		game = new Game(true);
		initMocks(this);

		doReturn(Stages.MAIN_MENU).when(stage).getType();
		doReturn(Stages.MISSIONS).when(missionsStage).getType();
		doReturn(stage).when(stageBuilder).getStage(any(StageResult.class));
	}

	@Test
	public void mainMenuIsFirstStage()
	{
		game.create();
		assertEquals(Stages.MAIN_MENU, game.getCurrentStage());
	}

	@Test
	public void gameIsLoadedDuringCreate()
	{
		game.create();
		verify(gameLoader).load();
	}

	@Test
	public void stageIsSwitchedToTypeReturnedByPreviousStage()
	{
		game.create();

		StageResult result = new StageResult();
		result.setNextStage(Stages.MISSIONS);

		doReturn(true).when(stage).isCompleted();
		doReturn(result).when(stage).getResult();
		doReturn(missionsStage).when(stageBuilder).getStage(eq(result));

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

		verify(extUtils, times(3)).clearScreen();
	}

	@Test
	public void stageIsActedAndRendered()
	{
		doReturn(true).when(frameController).check();

		game.create();
		game.render();
		game.render();

		verify(stage, times(2)).act(any(Float.class));
		verify(stage, times(2)).draw();
	}
}
