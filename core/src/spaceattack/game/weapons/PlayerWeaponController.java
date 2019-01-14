package spaceattack.game.weapons;

import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.weapons.IWeapon;

import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.vector.IVector;

public class PlayerWeaponController implements IWeaponController
{
	private IShip ship;

	private IWeapon primaryWeapon;
	private IWeapon secondaryWeapon;

	@SuppressWarnings("unused")
	private IFireButton primaryFireButton;
	private IFireButton secondaryFireButton;

	@Override
	public void setShip(IShip ship)
	{
		this.ship = ship;
	}

	@Override
	public void setPrimaryWeapon(IWeapon weapon)
	{
		primaryWeapon = weapon;
	}

	@Override
	public void setSecondaryWeapon(IWeapon weapon)
	{
		secondaryWeapon = weapon;
	}

	public void setPrimaryFireButton(IFireButton button)
	{
		primaryFireButton = button;
	}

	public void setSecondaryFireButton(IFireButton button)
	{
		secondaryFireButton = button;
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
	public IVector getWeaponMovement()
	{
		return Factories.getVectorFactory().create(0, 1);
	}

	@Override
	public boolean takeEnergy(float energyCost)
	{
		return ship.takeEnergy(energyCost);
	}

	@Override
	public void updateSecondaryWeapon(IWeapon weapon)
	{
		secondaryWeapon = weapon;
		secondaryFireButton.setWeapon(weapon);
	}
}
