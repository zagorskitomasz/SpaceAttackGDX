package spaceattack.game.powerup;

import spaceattack.game.buttons.weapon.ComplexFireButton;
import spaceattack.game.weapons.IWeapon;

public class WeaponHolder extends AbstractPowerUp
{
	private IWeapon weapon;
	private ComplexFireButton button;
	private int ammo;

	public void setWeapon(IWeapon weapon)
	{
		this.weapon = weapon;
	}

	public IWeapon getWeapon()
	{
		return weapon;
	}

	public void setFireButton(ComplexFireButton button)
	{
		this.button = button;
	}

	public void setAmmo(int ammo)
	{
		this.ammo = ammo;
	}

	public int getAmmo()
	{
		return ammo;
	}

	@Override
	public void consumed()
	{
		button.setSpecialWeapon(weapon, ammo);
	}
}
