package spaceattack.game.weapons.missiles;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.interfaces.Vulnerable;
import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.MissilesLauncher;

public class ExplosiveMissileTest
{
	@InjectMocks
	private ExplosiveMissile missile;

	@Mock
	private Explosion explosion;

	@Mock
	private MissilesLauncher launcher;
	
	@Mock
	private Vulnerable vulnerable;

	@Before
	public void setUp()
	{
		missile = new ExplosiveMissile();
		MockitoAnnotations.initMocks(this);
		missile.setActor(new FakeActor());
		doReturn(new FakeActor()).when(explosion).getActor();
		doReturn(ExtVectorFactory.INSTANCE.create(100, 100)).when(vulnerable).getPosition();
		Factories.setVectorFactory(ExtVectorFactory.INSTANCE);
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
	}

	@Test
	public void launchesExplosionWhenKilled()
	{
		missile.setToKill();
		verify(launcher).launch(explosion);
	}
	
	@Test
	public void wontExplodeOutOfScreen()
	{
		missile.setX(-100);
		missile.setToKill();

		verify(launcher,times(0)).launch(explosion);
	}
	
	@Test
	public void willDissapearIfOutOfScreen()
	{
		missile.setX(-100);
		missile.checkCollision(vulnerable);

		assertTrue(missile.isToKill());
	}
}
