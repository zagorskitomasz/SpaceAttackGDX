package spaceattack.game.ai.movers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.utils.vector.IVectorFactory;

public class RightSideChaserTest
{
	private RightSideChaser mover;

	private FakeShip playerShip;
	private IVectorFactory vectors;

	@Mock
	private IEnemyShip owner;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		playerShip = new FakeShip();
		vectors = ExtVectorFactory.INSTANCE;
		Factories.setVectorFactory(vectors);
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

		playerShip.setX(100);
		playerShip.setY(200);
		playerShip.setRadius(15);

		mover = new RightSideChaser();
		mover.setPlayerShip(playerShip);
		mover.setOwner(owner);
	}

	@Test
	public void keepRightFromPlayerShip()
	{
		doReturn(false).when(owner).isMoving();
		doReturn(vectors.create(400, 500)).when(owner).getPosition();

		mover.updateDirection();

		verify(owner).setDestination(eq(vectors.create(130, 200)));
	}
}
