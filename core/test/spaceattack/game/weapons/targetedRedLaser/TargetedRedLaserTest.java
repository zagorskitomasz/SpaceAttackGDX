package spaceattack.game.weapons.targetedRedLaser;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IActorFactory;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Missile;

public class TargetedRedLaserTest
{
	@Mock
	private IWeaponController controller;

	@Mock
	private MissilesLauncher launcher;

	@Mock
	private IActorFactory factory;

	private TargetedRedLaser laser;

	@Before
	public void setUp()
	{
		initMocks(this);
		Textures.loadForTest();
		Factories.setVectorFactory(ExtVectorFactory.INSTANCE);
		Factories.setActorFactory(factory);

		laser = new TargetedRedLaser();

		laser.setUtils(ExtUtilsFactory.INSTANCE.create());
		laser.setController(controller);
		laser.setMissilesLauncher(launcher);
		laser.setLevel(1);

		doReturn(true).when(controller).takeEnergy(anyFloat());
		doReturn(ExtVectorFactory.INSTANCE.create(100, 100)).when(controller).getPrimaryWeaponUsePlacement();
		doReturn(new FakeActor()).when(factory).create(any(IGameActor.class));
	}

	@Test
	public void dontUseEnergy() throws InterruptedException
	{
		Thread.sleep(500);
		doReturn(ExtVectorFactory.INSTANCE.create(1, 1)).when(controller).getTargetedWeaponMovement();
		laser.use();
		verify(controller).takeEnergy(0f);
	}
	
	@Test
	public void settingProperTexture()
	{
		doReturn(ExtVectorFactory.INSTANCE.create(1, 1)).when(controller).getTargetedWeaponMovement();
		laser.buildMissile();
		assertEquals(Textures.RED_LASER_S2, laser.getTexture());

		doReturn(ExtVectorFactory.INSTANCE.create(1, 0)).when(controller).getTargetedWeaponMovement();
		laser.buildMissile();
		assertEquals(Textures.RED_LASER_WE, laser.getTexture());

		doReturn(ExtVectorFactory.INSTANCE.create(1, -1)).when(controller).getTargetedWeaponMovement();
		laser.buildMissile();
		assertEquals(Textures.RED_LASER_S1, laser.getTexture());

		doReturn(ExtVectorFactory.INSTANCE.create(0, -1)).when(controller).getTargetedWeaponMovement();
		laser.buildMissile();
		assertEquals(Textures.RED_LASER_NS, laser.getTexture());

		doReturn(ExtVectorFactory.INSTANCE.create(-1, -1)).when(controller).getTargetedWeaponMovement();
		laser.buildMissile();
		assertEquals(Textures.RED_LASER_S2, laser.getTexture());

		doReturn(ExtVectorFactory.INSTANCE.create(-1, 0)).when(controller).getTargetedWeaponMovement();
		laser.buildMissile();
		assertEquals(Textures.RED_LASER_WE, laser.getTexture());

		doReturn(ExtVectorFactory.INSTANCE.create(-1, 1)).when(controller).getTargetedWeaponMovement();
		laser.buildMissile();
		assertEquals(Textures.RED_LASER_S1, laser.getTexture());

		doReturn(ExtVectorFactory.INSTANCE.create(0, 1)).when(controller).getTargetedWeaponMovement();
		laser.buildMissile();
		assertEquals(Textures.RED_LASER_NS, laser.getTexture());
	}
	
	@Test
	public void settingProperShotPlacement()
	{
		Missile missile;
		
		doReturn(10f).when(controller).getShipsWidth();
		doReturn(20f).when(controller).getShipsHeight();

		doReturn(ExtVectorFactory.INSTANCE.create(1, 1)).when(controller).getTargetedWeaponMovement();
		missile = laser.buildMissile();
		assertEquals(ExtVectorFactory.INSTANCE.create(107, 114), missile.getPosition());

		doReturn(ExtVectorFactory.INSTANCE.create(1, 0)).when(controller).getTargetedWeaponMovement();
		missile = laser.buildMissile();
		assertEquals(ExtVectorFactory.INSTANCE.create(107, 100), missile.getPosition());

		doReturn(ExtVectorFactory.INSTANCE.create(1, -1)).when(controller).getTargetedWeaponMovement();
		missile = laser.buildMissile();
		assertEquals(ExtVectorFactory.INSTANCE.create(107, 86), missile.getPosition());

		doReturn(ExtVectorFactory.INSTANCE.create(0, -1)).when(controller).getTargetedWeaponMovement();
		missile = laser.buildMissile();
		assertEquals(ExtVectorFactory.INSTANCE.create(100, 86), missile.getPosition());

		doReturn(ExtVectorFactory.INSTANCE.create(-1, -1)).when(controller).getTargetedWeaponMovement();
		missile = laser.buildMissile();
		assertEquals(ExtVectorFactory.INSTANCE.create(93, 86), missile.getPosition());

		doReturn(ExtVectorFactory.INSTANCE.create(-1, 0)).when(controller).getTargetedWeaponMovement();
		missile = laser.buildMissile();
		assertEquals(ExtVectorFactory.INSTANCE.create(93, 100), missile.getPosition());

		doReturn(ExtVectorFactory.INSTANCE.create(-1, 1)).when(controller).getTargetedWeaponMovement();
		missile = laser.buildMissile();
		assertEquals(ExtVectorFactory.INSTANCE.create(93, 114), missile.getPosition());

		doReturn(ExtVectorFactory.INSTANCE.create(0, 1)).when(controller).getTargetedWeaponMovement();
		missile = laser.buildMissile();
		assertEquals(ExtVectorFactory.INSTANCE.create(100, 114), missile.getPosition());
	}
}
