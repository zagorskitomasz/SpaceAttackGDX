package com.zagorskidev.spaceattack.system;

import org.junit.Test;
import org.mockito.Mockito;

import com.zagorskidev.spaceattack.ships.IShip;

public class GameProgressTest
{
	@Test
	public void notifingAboutLevelChange()
	{
		IShip ship = Mockito.mock(IShip.class);
		GameProgress progress = new GameProgress();
		progress.registerObserver(ship);
		progress.setLevel(progress.getLevel() + 1);

		Mockito.verify(ship).notify(progress);
	}
}
