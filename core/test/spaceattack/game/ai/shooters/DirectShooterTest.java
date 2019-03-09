package spaceattack.game.ai.shooters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.IWeaponController;

public class DirectShooterTest
{
	private ShooterAI shooter;

	private FakeShip playerShip;
	private List<FakeShip> friends;
	private IVectorFactory vectors;

	@Mock
	private IWeaponController controller;

	@Mock
	private IEnemyShip owner;

	@Before
	public void setUp()
	{
		vectors = ExtVectorFactory.INSTANCE;
		Factories.setVectorFactory(vectors);
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

		MockitoAnnotations.initMocks(this);
		doReturn(controller).when(owner).getWeaponController();
		doReturn(vectors.create(105, 400)).when(controller).getPrimaryWeaponUsePlacement();
		doReturn(vectors.create(105, 400)).when(controller).getSecondaryWeaponUsePlacement();
		doReturn(105f).when(owner).getX();
		doReturn(400f).when(owner).getY();

		playerShip = new FakeShip();
		friends = new LinkedList<>();

		playerShip.setX(100);
		playerShip.setY(200);
		playerShip.setRadius(15);

		shooter = new DirectShooter();
		shooter.setPlayerShip(playerShip);
		shooter.setOwner(owner);
		shooter.setFriends(friends);
	}

	@Test
	public void shootWhenInRadiusWidthLineWithPlayer()
	{
		doReturn(vectors.create(105, 400)).when(owner).getPosition();

		assertEquals(PossibleAttacks.BOTH, shooter.checkShot());
	}

	@Test
	public void shootOnlyWeaponInRadius()
	{
		doReturn(vectors.create(125, 400)).when(controller).getPrimaryWeaponUsePlacement();
		doReturn(vectors.create(125, 400)).when(controller).getSecondaryWeaponUsePlacement();
		doReturn(5f).when(controller).getPrimaryWeaponRadius();
		doReturn(15f).when(controller).getSecondaryWeaponRadius();

		assertEquals(PossibleAttacks.SECONDARY, shooter.checkShot());
	}

	@Test
	public void dontShootUnderTarget()
	{
		doReturn(vectors.create(105, 190)).when(controller).getPrimaryWeaponUsePlacement();
		doReturn(vectors.create(105, 100)).when(controller).getSecondaryWeaponUsePlacement();

		assertEquals(PossibleAttacks.PRIMARY, shooter.checkShot());
	}
	
	@Test
	public void dontShootAboveFriend()
	{
		FakeShip friend = new FakeShip();
		friend.setX(105);
		friend.setY(300);
		friends.add(friend);
		
		assertEquals(PossibleAttacks.NONE, shooter.checkShot());
	}
	
	@Test
	public void shootIfTargetBetweenOwnerAndFriend()
	{
		FakeShip friend = new FakeShip();
		friend.setX(105);
		friend.setY(100);
		friends.add(friend);
		
		assertEquals(PossibleAttacks.BOTH, shooter.checkShot());
	}
}
