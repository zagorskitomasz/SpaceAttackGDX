package spaceattack.game.ai.movers;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.notifiers.IObserver;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class CorrectableJumperTest 
{
	private IVectorFactory vectors;
	
	private MoverAI jumper;
	
	private IShip playerShip;
	
	private IEnemyShip owner;
	
	@Mock
	private IObserver<MoverAI> observer;
	
	@Before
	public void setUp()
	{
		vectors = ExtVectorFactory.INSTANCE;
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
		Factories.setVectorFactory(vectors);
		MockitoAnnotations.initMocks(this);
		
		jumper = new CorrectableJumper();
		playerShip = new FakeShip();
		owner = spy(new FakeShip());
		doNothing().when(owner).setDestination(any(IVector.class));
		jumper.setOwner(owner);
		jumper.setPlayerShip(playerShip);
		jumper.registerObserver(observer);
		
		owner.setX(100);
		owner.setY(100);
		
		playerShip.setX(800);
		playerShip.setY(800);
	}

	@Test
	public void jumperIsChasingTarget() 
	{
		jumper.updateDirection();
		verify(owner).setDestination(vectors.create(800, 800));
	}
	
	@Test
	public void jumperWillCorrectAfterDelay() throws InterruptedException
	{
		jumper.updateDirection();
		playerShip.setX(850);
		playerShip.setY(850);
		Thread.sleep((1010));
		jumper.updateDirection();
		verify(owner).setDestination(vectors.create(850, 850));
	}
	
	@Test
	public void jumperWontCorrectBeforeDelay()
	{
		jumper.updateDirection();
		playerShip.setX(850);
		playerShip.setY(850);
		verify(owner,never()).setDestination(vectors.create(850, 850));
	}
	
	@Test
	public void ifDestinationReachedObserversAreNotified()
	{
		playerShip.setX(150);
		playerShip.setY(150);
		jumper.updateDirection();
		
		verify(observer).notify(jumper);
	}
	
	@Test
	public void ifDestinationReachedJumperStartsEscaping()
	{
		doAnswer(invocation -> {
			IVector destination = invocation.getArgument(0);
			System.out.println(destination);
			assertTrue(destination.getY() > 1300);
			return null;
		}).when(owner).setDestination(any(IVector.class));
		
		playerShip.setX(150);
		playerShip.setY(150);
		jumper.updateDirection();
	}
	
	@Test
	public void ifEscapedStartCharging()
	{
		playerShip.setX(150);
		playerShip.setY(150);
		jumper.updateDirection();
		
		owner.setX(100);
		owner.setY(1500);
		playerShip.setX(222);
		playerShip.setY(222);
		jumper.updateDirection();
		
		verify(owner).setDestination(vectors.create(222, 222));
	}
}
