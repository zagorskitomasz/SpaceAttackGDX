package spaceattack.game.weapons;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.FakeShip;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class AIWeaponControllerTest
{
	private IShip ship;
	private FakeShip target;

	@Mock
	private IWeapon weapon1;

	@Mock
	private IWeapon weapon2;

	private IWeaponController controller;
	
	private IVectorFactory vectors;

	@Before
	public void setUp()
	{
		vectors = ExtVectorFactory.INSTANCE;
		Factories.setVectorFactory(vectors);
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
		ship = new FakeShip();
		target = new FakeShip();
		MockitoAnnotations.initMocks(this);
		controller = new AIWeaponController();

		controller.setPrimaryWeapon(weapon1);
		controller.setSecondaryWeapon(weapon2);
		controller.setShip(ship);

		doReturn(20f).when(weapon1).getEnergyCost();
		doReturn(10f).when(weapon2).getEnergyCost();
		
		ship.setX(100);
		ship.setY(100);
	}

	@Test
	public void whenBothPossibleAndCanUseSecondaryUseSecondary()
	{
		doReturn(true).when(weapon2).use();

		controller.performAttack(PossibleAttacks.BOTH,null);

		verify(weapon2).use();
		verify(weapon1, times(0)).use();
	}

	@Test
	public void whenBothPossibleAndCantUseSecondaryUsePrimary()
	{
		doReturn(false).when(weapon2).use();

		controller.performAttack(PossibleAttacks.BOTH,null);

		verify(weapon1).use();
	}

	@Test
	public void whenPrimaryPossibleAndCantUsePrimaryDontTrySecondary()
	{
		doReturn(false).when(weapon1).use();

		controller.performAttack(PossibleAttacks.PRIMARY,null);

		verify(weapon2, times(0)).use();
	}

	@Test
	public void whenPrimaryPossibleAndCanUseItDontTrySecondary()
	{
		doReturn(true).when(weapon1).use();

		controller.performAttack(PossibleAttacks.PRIMARY,null);

		verify(weapon2, times(0)).use();
	}
	
	@Test
	public void calculatingProperTargetedAttackMovement()
	{
		IVector movement;
		controller.performAttack(PossibleAttacks.BOTH, target);
		
		target.setX(50);
		target.setY(40);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(-0.7f, -0.7f), movement);
		
		target.setX(50);
		target.setY(90);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(-1f, 0f), movement);
		
		target.setX(50);
		target.setY(110);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(-1f, 0f), movement);
		
		target.setX(50);
		target.setY(160);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(-0.7f, 0.7f), movement);
		
		target.setX(90);
		target.setY(160);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(0f, 1f), movement);
		
		target.setX(110);
		target.setY(160);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(0f, 1f), movement);
		
		target.setX(140);
		target.setY(160);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(0.7f, 0.7f), movement);
		
		target.setX(140);
		target.setY(110);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(1f, 0f), movement);
		
		target.setX(140);
		target.setY(90);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(1f, 0f), movement);
		
		target.setX(140);
		target.setY(50);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(0.7f, -0.7f), movement);
		
		target.setX(110);
		target.setY(50);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(0f, -1f), movement);
		
		target.setX(110);
		target.setY(40);
		movement = controller.getTargetedWeaponMovement();
		assertEquals(vectors.create(0f, -1f), movement);
	}
}
