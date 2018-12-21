package com.zagorskidev.spaceattack.weapons.redLaser;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.zagorskidev.spaceattack.graphics.TexturesFactory;
import com.zagorskidev.spaceattack.weapons.IMissileLauncher;
import com.zagorskidev.spaceattack.weapons.IWeaponController;
import com.zagorskidev.spaceattack.weapons.WeaponFactory;

public class RedLaserTest
{
	@Mock
	private IWeaponController controller;

	@Mock
	private IMissileLauncher launcher;

	@Mock
	private TexturesFactory texturesFactory;

	@Before
	public void setUp()
	{
		initMocks(this);
	}

	@Test
	public void builderIsSettingProperValues()
	{

		//@formatter:off
		RedLaser redLaser = WeaponFactory
				.INSTANCE
				.redLaser()
				.setController(controller)
				.setMissileLauncher(launcher)
				.setLevel(1)
				.build();
		//@formatter:on

		assertEquals(controller, redLaser.getController());
		assertEquals(launcher, redLaser.getLauncher());
	}
}
