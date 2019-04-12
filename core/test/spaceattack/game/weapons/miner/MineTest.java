package spaceattack.game.weapons.miner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.consts.Consts;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IActor;
import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Explosion;

public class MineTest 
{
	private static final long DELAY = 2000;
	
	@Mock
	private MissilesLauncher launcher;
	
	@Mock
	private Explosion explosion;
	
	@Mock
	private IActor explosionActor;
	
	private IActor actor;
	
	@InjectMocks
	private Mine mine;
	
	@Before
	public void setUp()
	{
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
		
		actor = new FakeActor();
		actor.setPosition(100, 100);
		
		mine = new Mine(DELAY);
		MockitoAnnotations.initMocks(this);
		mine.setActor(actor);
		
		doReturn(explosionActor).when(explosion).getActor();
	}

	@Test
	public void mineWontExplodeBeforeDelay() 
	{
		mine.act(0);
		verify(launcher,never()).launch(explosion);
	}

	@Test
	public void mineExplodeAfterDelay() throws InterruptedException 
	{
		Thread.sleep(DELAY);
		mine.act(0);
		verify(launcher).launch(explosion);
	}
	
	@Test
	public void overheatedExplodesSoon() throws InterruptedException
	{
		mine.overheat();
		Thread.sleep(Consts.Weapons.MINE_OVERHEAT_DELAY + 100);
		mine.act(0);
		verify(launcher).launch(explosion);
	}
}
