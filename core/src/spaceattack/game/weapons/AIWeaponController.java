package spaceattack.game.weapons;

import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.vector.IVector;

public class AIWeaponController extends AbstractWeaponController
{
	@Override
	public IVector getWeaponMovement()
	{
		return Factories.getVectorFactory().create(0, -1);
	}

	@Override
	public IVector getPrimaryWeaponUsePlacement()
	{
		return Factories.getVectorFactory().create(ship.getX(),
				ship.getY() - ship.getHeight() * primaryWeapon.getWeaponsMovementFactor());
	}

	@Override
	public IVector getSecondaryWeaponUsePlacement()
	{
		return Factories.getVectorFactory().create(ship.getX(),
				ship.getY() - ship.getHeight() * secondaryWeapon.getWeaponsMovementFactor());
	}

	@Override
	public void performAttack(PossibleAttacks possibleAttack)
	{
		switch (possibleAttack)
		{
			case BOTH:
				if (!tryUse(primaryWeapon))
					tryUse(secondaryWeapon);
				break;
			case PRIMARY:
				tryUse(primaryWeapon);
				break;
			case SECONDARY:
				tryUse(secondaryWeapon);
				break;
			default:
				break;
		}
	}

	private boolean tryUse(IWeapon weapon)
	{
		return weapon.use();
	}
}
