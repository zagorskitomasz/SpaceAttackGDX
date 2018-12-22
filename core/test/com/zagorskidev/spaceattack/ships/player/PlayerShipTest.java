package com.zagorskidev.spaceattack.ships.player;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class PlayerShipTest
{
	@Test
	public void isSettingCenterCoords()
	{
		Texture texture = mock(Texture.class);
		doReturn(100).when(texture).getWidth();
		doReturn(200).when(texture).getHeight();

		Batch batch = mock(Batch.class);

		PlayerShip ship = spy(new PlayerShip());
		doReturn(texture).when(ship).createTexture(any());
		ship.loadGraphics("");
		ship.draw(batch, 0);

		verify(batch).draw(texture, 150f, 125.000015f);
	}

	@Test
	public void radius()
	{
		PlayerShip ship = spy(PlayerShip.class);
		doReturn(100f).when(ship).getWidth();
		doReturn(100f).when(ship).getHeight();

		assertEquals(50f, ship.getRadius(), 0);
	}
}
