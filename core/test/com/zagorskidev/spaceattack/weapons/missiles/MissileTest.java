package com.zagorskidev.spaceattack.weapons.missiles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zagorskidev.spaceattack.ships.Ship;
import com.zagorskidev.spaceattack.sound.Sounds;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;

public class MissileTest
{
	private Missile missile;

	@Mock
	private Ship ship;

	@Mock
	private FireButton button;

	@Mock
	private Texture texture;

	private Array<Actor> actors;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		//@formatter:off
		missile = MissilesBuilder
				.INSTANCE
				.init()
				.setTexture(texture)
				.setDmg(10)
				.setSpeed(10)
				.setAcceleration(3)
				.setMovement(new Vector2(1,0))
				.setPosition(new Vector2(200,200))
				.setSound(Sounds.RED_LASER)
				.build();
		//@formatter:on

		actors = new Array<>();
		actors.add(ship);
		actors.add(button);

		missile.setActors(actors);

		doCallRealMethod().when(ship).getPosition();
		doReturn(100f).when(ship).getX();
		doReturn(100f).when(ship).getY();
		doReturn(10f).when(ship).getRadius();
		doReturn(20).when(texture).getHeight();
	}

	@Test
	public void missileIsMoving()
	{
		missile.act(0);
		missile.act(0);
		missile.act(0);

		assertEquals(new Vector2(239, 200), missile.getPosition());
	}

	@Test
	public void missileIsHittingVulnerableTarget()
	{
		doReturn(200f).when(ship).getX();
		doReturn(200f).when(ship).getY();

		missile.act(0);

		verify(ship).takeDmg(10f);
	}

	@Test
	public void isNotHittingOutOfRange()
	{
		missile.act(0);

		verify(ship, times(0)).takeDmg(10f);
	}

	@Test
	public void missileIsDissapearingAfterHit()
	{
		doReturn(200f).when(ship).getX();
		doReturn(200f).when(ship).getY();

		missile.act(0);

		assertTrue(missile.isToKill());
	}
}
