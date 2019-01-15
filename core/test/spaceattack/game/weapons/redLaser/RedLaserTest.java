package spaceattack.game.weapons.redLaser;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.consts.Consts;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Missile;

public class RedLaserTest
{
	@Mock
	private IWeaponController controller;

	@Mock
	private MissilesLauncher launcher;

	private RedLaser redLaser;

	@Before
	public void setUp()
	{
		initMocks(this);
		Textures.loadForTest();
		Factories.setVectorFactory(ExtVectorFactory.INSTANCE);

		redLaser = new RedLaser();

		redLaser.setUtils(ExtUtilsFactory.INSTANCE.create());
		redLaser.setController(controller);
		redLaser.setMissilesLauncher(launcher);
		redLaser.setLevel(1);

		doReturn(true).when(controller).takeEnergy(anyFloat());
	}

	@Test
	public void useIsLaunchingMissile()
	{
		redLaser.use();
		verify(launcher).launch(any(Missile.class));
	}

	@Test
	public void laserCantBeShootedTooOften()
	{
		redLaser.use();
		redLaser.use();
		verify(launcher, times(1)).launch(any(Missile.class));
	}

	@Test
	public void laserCantBeShootedAfterInterval() throws InterruptedException
	{
		redLaser.use();
		Thread.sleep(500);
		redLaser.use();
		verify(launcher, times(2)).launch(any(Missile.class));
	}

	@Test
	public void buildingRedLaserMissile()
	{
		Missile missile = redLaser.buildMissile();

		assertEquals(Consts.Weapons.RED_LASER_BASE_DMG, missile.getDmg(), 0);
		assertEquals(Consts.Weapons.RED_LASER_BASE_SPEED, missile.getSpeed(), 0);
		assertEquals(0, missile.getAcceleration(), 0);
	}

	@Test
	public void buildingHigherLevelMissile()
	{
		redLaser.setLevel(4);
		Missile missile = redLaser.buildMissile();

		assertEquals(Consts.Weapons.RED_LASER_BASE_DMG + 3 * Consts.Weapons.RED_LASER_DMG_PER_LEVEL, missile.getDmg(),
				0);
		assertEquals(Consts.Weapons.RED_LASER_BASE_SPEED + 3 * Consts.Weapons.RED_LASER_SPEED_PER_LEVEL,
				missile.getSpeed(), 0);
		assertEquals(0, missile.getAcceleration(), 0);
	}

	@Test
	public void cantShotWithoutEnergy()
	{
		doReturn(false).when(controller).takeEnergy(anyFloat());
		redLaser.use();
		verify(launcher, times(0)).launch(any(Missile.class));
	}
}