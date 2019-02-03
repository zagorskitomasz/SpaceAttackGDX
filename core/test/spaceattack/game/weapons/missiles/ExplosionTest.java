package spaceattack.game.weapons.missiles;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.FakeVulnerable;
import spaceattack.game.animations.IAnimation;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.vector.IVectorFactory;

public class ExplosionTest
{
	private IVectorFactory factory;

	private Explosion explosion;

	@Mock
	private ITexture texture;

	@Mock
	private FakeVulnerable vulnerable;

	@Mock
	private IShip ignitable;

	@Mock
	private Sounds sound;

	@Mock
	private IAnimation animation;

	private List<IGameActor> actors;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		factory = ExtVectorFactory.INSTANCE;
		Factories.setVectorFactory(factory);
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

		explosion = new Explosion();
		explosion.setActor(new FakeActor());
		explosion.setAnimation(animation);
		explosion.setDmg(10);
		explosion.setFireDmg(5);
		explosion.setFireDuration(2000);
		explosion.setRadius(20);
		explosion.setPosition(factory.create(200, 200));
		explosion.setSound(sound);

		actors = new ArrayList<>();
		actors.add(vulnerable);
		actors.add(ignitable);

		doReturn(factory.create(210, 210)).when(ignitable).getPosition();
		doReturn(10f).when(ignitable).getRadius();
		doReturn(factory.create(190, 190)).when(vulnerable).getPosition();
		doReturn(10f).when(vulnerable).getRadius();
	}

	@Test
	public void explosionHitsVulnerablesInRange()
	{
		explosion.setActors(actors);

		verify(vulnerable).takeDmg(10f);
	}

	@Test
	public void explosionIgnitesIgnitableInRange()
	{
		explosion.setActors(actors);

		verify(ignitable).ignite(5f, 2000l);
	}

	@Test
	public void explosionWontHitActorsOutOfRange()
	{
		doReturn(factory.create(100, 100)).when(ignitable).getPosition();

		explosion.setActors(actors);

		verify(ignitable, times(0)).takeDmg(anyFloat());
		verify(ignitable, times(0)).ignite(anyFloat(), anyLong());
	}

	@Test
	public void whenAnimationCompletedExplosionIsRemoved()
	{
		doReturn(true).when(animation).isCompleted();

		explosion.act(10);

		assertTrue(explosion.isToKill());
	}
}
