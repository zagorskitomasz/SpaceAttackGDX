package com.zagorskidev.spaceattack.weapons.redLaser;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.badlogic.gdx.graphics.Texture;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.graphics.TexturesFactory;
import com.zagorskidev.spaceattack.weapons.IMissileLauncher;
import com.zagorskidev.spaceattack.weapons.IWeaponController;
import com.zagorskidev.spaceattack.weapons.WeaponFactory;
import com.zagorskidev.spaceattack.weapons.missiles.Missile;

public class RedLaserTest
{
	@Mock
	private IWeaponController controller;

	@Mock
	private IMissileLauncher launcher;

	@Mock
	private TexturesFactory texturesFactory;

	@Mock
	private Missile missile;

	private RedLaser redLaser;

	@Before
	public void setUp()
	{
		initMocks(this);

		//@formatter:off
		redLaser = WeaponFactory
				.INSTANCE
				.redLaser()
				.setController(controller)
				.setMissileLauncher(launcher)
				.setLevel(1)
				.build();
		//@formatter:on

		redLaser = spy(redLaser);
	}

	@Test
	public void builderIsSettingProperValues()
	{
		assertEquals(controller, redLaser.getController());
		assertEquals(launcher, redLaser.getLauncher());
	}

	@Test
	public void useIsLaunchingMissile()
	{
		doReturn(missile).when(redLaser).buildMissile();
		redLaser.use();
		verify(launcher).launch(missile);
	}

	@Test
	public void laserCantBeShootedTooOften()
	{
		doReturn(missile).when(redLaser).buildMissile();
		redLaser.use();
		redLaser.use();
		verify(launcher, times(1)).launch(missile);
	}

	@Test
	public void laserCantBeShootedAfterInterval() throws InterruptedException
	{
		doReturn(missile).when(redLaser).buildMissile();
		redLaser.use();
		Thread.sleep(500);
		redLaser.use();
		verify(launcher, times(2)).launch(missile);
	}

	@Test
	public void buildingRedLaserMissile()
	{
		doReturn(mock(Texture.class)).when(redLaser).getMissileTexture();
		Missile missile = redLaser.buildMissile();

		assertEquals(Consts.Weapons.RED_LASER_BASE_DMG, missile.getDmg(), 0);
		assertEquals(Consts.Weapons.RED_LASER_BASE_SPEED, missile.getSpeed(), 0);
		assertEquals(0, missile.getAcceleration(), 0);
	}

	@Test
	public void buildingHigherLevelMissile()
	{
		doReturn(mock(Texture.class)).when(redLaser).getMissileTexture();
		redLaser.setLevel(4);
		Missile missile = redLaser.buildMissile();

		assertEquals(Consts.Weapons.RED_LASER_BASE_DMG + 3 * Consts.Weapons.RED_LASER_DMG_PER_LEVEL, missile.getDmg(),
				0);
		assertEquals(Consts.Weapons.RED_LASER_BASE_SPEED + 3 * Consts.Weapons.RED_LASER_SPEED_PER_LEVEL,
				missile.getSpeed(), 0);
		assertEquals(0, missile.getAcceleration(), 0);
	}
}
