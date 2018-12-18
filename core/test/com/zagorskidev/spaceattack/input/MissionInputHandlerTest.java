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
import com.zagorskidev.spaceattack.ui.buttons.FireButton;

public class MissionInputHandlerTest
{
	@Mock
	private IShip ship;

	@Mock
	private FireButton button;

	private MissionInputHandler handler;

	@Before
	public void setUp()
	{
		initMocks(this);

		handler = new MissionInputHandler();
	}

	@Test
	public void inputIsSettingShipsDestination()
	{
		handler.registerShip(ship);
		handler.touchUp(100, 200, 1, 1);

		verify(ship).setDestination(eq(new Vector2(100, 550)));
	}

	@Test
	public void ifWeaponFiredShipIsNotMoved()
	{
		doReturn(true).when(button).touch(anyInt(), anyInt());

		handler.registerShip(ship);
		handler.registerFireButton(button);
		handler.touchUp(100, 200, 1, 1);

		verify(ship, times(0)).setDestination(any(Vector2.class));
	}
}
