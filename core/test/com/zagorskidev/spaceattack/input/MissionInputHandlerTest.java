package com.zagorskidev.spaceattack.input;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.badlogic.gdx.math.Vector2;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.stages.IGameOverChecker;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;

public class MissionInputHandlerTest
{
	@Mock
	private IShip ship;

	@Mock
	private FireButton button;

	@Mock
	private IGameOverChecker gameOverChecker;

	private MissionInputHandler handler;

	@Before
	public void setUp()
	{
		initMocks(this);

		handler = new MissionInputHandler(gameOverChecker);
	}

	@Test
	public void inputIsSettingShipsDestination()
	{
		doReturn(false).when(button).touchUp(anyInt(), anyInt());

		handler.registerShip(ship);
		handler.registerFireButton(button);
		handler.touchUp(100, 200, 1, 1);

		verify(ship).setDestination(eq(new Vector2(100, 550)));
	}

	@Test
	public void ifWeaponFiredShipIsNotMoved()
	{
		doReturn(true).when(button).touchUp(anyInt(), anyInt());

		handler.registerShip(ship);
		handler.registerFireButton(button);
		handler.touchUp(100, 200, 1, 1);

		verify(ship, times(0)).setDestination(any(Vector2.class));
	}

	@Test
	public void ifGameOverOnlyFinalizeStageIsFired()
	{
		doReturn(true).when(gameOverChecker).isGameOver();

		handler.registerShip(ship);
		handler.registerFireButton(button);
		handler.touchUp(100, 200, 1, 1);

		verify(ship, times(0)).setDestination(any(Vector2.class));
		verify(button, times(0)).touchUp(anyInt(), anyInt());

		verify(gameOverChecker).finalizeStage();
	}
}
