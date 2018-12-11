package com.zagorskidev.spaceattack.ships.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
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

		IShip ship = new PlayerShip(null, texture);
		ship.draw(batch, 0);

		Mockito.verify(batch).draw(texture, 150f, 125.000015f);
	}

	@Test
	public void shipIsMovingToDestination()
	{
		Ship ship = new PlayerShip(null, null);
		ship.setDestination(new Vector2(50, 100));
		ship.act(0);

		assertEquals(50, ship.getX(), 0);
		assertEquals(100, ship.getY(), 0);
	}
}
