package spaceattack.game.ships.enemy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.utils.Rect;

public class EnemyBarTest
{
	private EnemyBar bar;

	@Mock
	private IBatch batch;

	@Mock
	private IPool pool;

	private IShip ship;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		ship = new FakeShip();
		ship.setHpPool(pool);
		doReturn(10f).when(pool).getAmount();
		doReturn(20f).when(pool).getMaxAmount();

		bar = new EnemyBar(ship);
	}

	@Test
	public void twoRectsAreDrawn()
	{
		bar.draw(batch);
		verify(batch).rect(any(Rect.class), any(Rect.class));
	}
}
