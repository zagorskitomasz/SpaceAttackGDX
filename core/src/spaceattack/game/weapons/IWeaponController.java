package spaceattack.game.weapons;

import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.vector.IVector;

public interface IWeaponController
{
	public void setShip(IShip ship);

	public void setPrimaryWeapon(IWeapon weapon);

	public void setSecondaryWeapon(IWeapon weapon);

	public IVector getPrimaryWeaponUsePlacement();

	public IVector getSecondaryWeaponUsePlacement();

	public IVector getWeaponMovement();

	public boolean takeEnergy(float energyCost);

	public void updateSecondaryWeapon(IWeapon weapon);

	public float getPrimaryWeaponRadius();

	public float getSecondaryWeaponRadius();

	public void performAttack(PossibleAttacks possibleAttack);

	public boolean isPlayer();
}
