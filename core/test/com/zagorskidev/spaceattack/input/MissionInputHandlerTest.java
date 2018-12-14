package com.zagorskidev.spaceattack.input;

import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.math.Vector2;
import com.zagorskidev.spaceattack.ships.IShip;

public class MissionInputHandlerTest
{
	@Test
	public void inputIsSettingShipsDestination()
	{
		IShip ship = Mockito.mock(IShip.class);
		MissionInputHandler handler = new MissionInputHandler();
		handler.registerShip(ship);
		handler.touchUp(100, 200, 1, 1);

		Mockito.verify(ship).setDestination(Mockito.eq(new Vector2(100, 550)));
	}
}
