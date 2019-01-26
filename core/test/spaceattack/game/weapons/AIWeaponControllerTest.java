package spaceattack.game.weapons;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.ships.IShip;

public class AIWeaponControllerTest
{
	@Mock
	private IShip ship;

	@Mock
	private IWeapon weapon1;

	@Mock
	private IWeapon weapon2;

	private IWeaponController controller;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		controller = new AIWeaponController();

		controller.setPrimaryWeapon(weapon1);
		controller.setSecondaryWeapon(weapon2);
		controller.setShip(ship);

		doReturn(20f).when(weapon1).getEnergyCost();
		doReturn(10f).when(weapon2).getEnergyCost();
	}

	@Test
	public void whenBothPossibleAndCanUsePrimaryUsePrimary()
	{
		doReturn(true).when(weapon1).use();

		controller.performAttack(PossibleAttacks.BOTH);

		verify(weapon1).use();
		verify(weapon2, times(0)).use();
	}

	@Test
	public void whenBothPossibleAndCantUsePrimaryUseSecond()
	{
		doReturn(false).when(weapon1).use();

		controller.performAttack(PossibleAttacks.BOTH);

		verify(weapon2).use();
	}

	@Test
	public void whenPrimaryPossibleAndCantUsePrimaryDontTrySecondary()
	{
		doReturn(false).when(weapon1).use();

		controller.performAttack(PossibleAttacks.PRIMARY);

		verify(weapon2, times(0)).use();
	}

	@Test
	public void whenPrimaryPossibleAndCanUseItDontTrySecondary()
	{
		doReturn(true).when(weapon1).use();

		controller.performAttack(PossibleAttacks.PRIMARY);

		verify(weapon2, times(0)).use();
	}
}
