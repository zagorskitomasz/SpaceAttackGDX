package com.zagorskidev.spaceattack.weapons.missiles;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class MissileTest
{
	@Test
	public void missileIsMoving()
	{
		//@formatter:off
		Missile missile = MissilesBuilder
				.INSTANCE
				.init()
				.setTexture(Mockito.mock(Texture.class))
				.setDmg(10)
				.setSpeed(10)
				.setAcceleration(3)
				.setMovement(new Vector2(1,0))
				.setPosition(new Vector2(200,200))
				.build();
		//@formatter:on

		missile.act(0);
		missile.act(0);
		missile.act(0);

		assertEquals(new Vector2(239, 200), missile.getPosition());
	}
}
