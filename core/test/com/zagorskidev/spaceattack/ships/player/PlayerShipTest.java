package com.zagorskidev.spaceattack.ships.player;

import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.zagorskidev.spaceattack.ships.IShip;

public class PlayerShipTest
{

	@Test
	public void isSettingCenterCoords()
	{
		Texture texture = Mockito.mock(Texture.class);
		Mockito.doReturn(100).when(texture).getWidth();
		Mockito.doReturn(200).when(texture).getHeight();

		Batch batch = Mockito.mock(Batch.class);

		IShip ship = new PlayerShip(texture);
		ship.draw(batch, 0);

		Mockito.verify(batch).draw(texture, 150f, 125.000015f);
	}
}
