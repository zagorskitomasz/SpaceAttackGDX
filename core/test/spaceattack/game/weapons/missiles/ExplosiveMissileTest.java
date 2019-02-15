package spaceattack.game.weapons.missiles;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.actors.FakeActor;
import spaceattack.game.weapons.MissilesLauncher;

public class ExplosiveMissileTest
{
	@InjectMocks
	private ExplosiveMissile missile;

	@Mock
	private Explosion explosion;

	@Mock
	private MissilesLauncher launcher;

	@Before
	public void setUp()
	{
		missile = new ExplosiveMissile();
		MockitoAnnotations.initMocks(this);
		missile.setActor(new FakeActor());
		doReturn(new FakeActor()).when(explosion).getActor();
	}

	@Test
	public void launchesExplosionWhenKilled()
	{
		missile.setToKill();
		verify(launcher).launch(explosion);
	}
}
