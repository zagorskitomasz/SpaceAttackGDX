package spaceattack.game.system.graphics;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.actors.IActorFactory;
import spaceattack.game.factories.Factories;

public class StaticImageFactoryTest
{
	private StaticImageFactory factory;

	@Mock
	private ITexture texture;

	@Mock
	private IActorFactory actorFactory;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		factory = StaticImageFactory.INSTANCE;
		Factories.setActorFactory(actorFactory);
	}

	@Test
	public void factoryIsCreatingImageObject()
	{
		StaticImage image = factory.create(texture, 100, 500);

		assertEquals(texture, image.getTexture());
		assertEquals(100, image.getX(), 0);
		assertEquals(200, image.getY(), 0);
	}
}
