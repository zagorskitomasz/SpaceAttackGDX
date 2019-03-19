package spaceattack.game.weapons;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.vector.IVector;

public class PlayerWeaponController extends AbstractWeaponController
{
	@SuppressWarnings("unused")
	private IFireButton primaryFireButton;
	private IFireButton secondaryFireButton;

	public void setPrimaryFireButton(IFireButton button)
	{
		primaryFireButton = button;
	}

	public void setSecondaryFireButton(IFireButton button)
	{
		secondaryFireButton = button;
	}

	@Override
	public IVector getWeaponMovement()
	{
		return Factories.getVectorFactory().create(0, 1);
	}

	@Override
	public void updateSecondaryWeapon(IWeapon weapon)
	{
		super.updateSecondaryWeapon(weapon);
		secondaryFireButton.setWeapon(weapon);
	}

	@Override
	public IVector getPrimaryWeaponUsePlacement()
	{
		return Factories.getVectorFactory().create(ship.getX(),
				ship.getY() + ship.getHeight() * primaryWeapon.getWeaponsMovementFactor());
	}

	@Override
	public IVector getSecondaryWeaponUsePlacement()
	{
		return Factories.getVectorFactory().create(ship.getX(),
				ship.getY() + ship.getHeight() * secondaryWeapon.getWeaponsMovementFactor());
	}

	@Override
	public void performAttack(PossibleAttacks possibleAttack, RadarVisible target)
	{
		// do nothing
	}

	@Override
	public boolean isPlayer() 
	{
		return true;
	}

	@Override
	public IVector getTargetedWeaponMovement() 
	{
		return getWeaponMovement();
	}
}
