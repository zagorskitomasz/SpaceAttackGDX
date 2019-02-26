package spaceattack.game.system.graphics;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.consts.Sizes;
import spaceattack.game.actors.IActorFactory;
import spaceattack.game.batch.IBatch;
import spaceattack.game.factories.Factories;

public class StaticImageTest
{
	@Mock
	private IBatch batch;

	@Mock
	private ITexture texture;

	@Mock
	private IActorFactory actorFactory;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		Factories.setActorFactory(actorFactory);
	}

	@Test
	public void drawsTextureOnBatchWithCoords()
	{
		StaticImage image = StaticImageFactory.INSTANCE.create(texture, 10, 480);

		image.draw(batch, 0);

		verify(batch).draw(texture, 10, Sizes.GAME_HEIGHT - 480, 0, 0);
	}
}
