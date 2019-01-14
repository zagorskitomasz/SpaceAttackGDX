package spaceattack.game.buttons.weapon;

import com.zagorskidev.spaceattack.weapons.IWeapon;

import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeaponController;

@SuppressWarnings("unused")
public class ComplexFireButton extends FireButton
{
	private ITexture drawableUp;
	private ITexture drawableDown;
	private ITexture specialDrawableUp;
	private ITexture specialDrawableDown;

	private IWeapon mainWeapon;

	private IWeaponController controller;

	public ComplexFireButton()
	{
		drawableUp = Textures.GREEN_BUTTON_UP.getTexture();
		drawableDown = Textures.GREEN_BUTTON_DOWN.getTexture();
		specialDrawableUp = Textures.YELLOW_BUTTON_UP.getTexture();
		specialDrawableDown = Textures.YELLOW_BUTTON_DOWN.getTexture();
	}

	public void setMainWeapon(IWeapon weapon)
	{
		mainWeapon = weapon;
		setWeapon(weapon);
	}

	public void setWeaponController(IWeaponController controller)
	{
		this.controller = controller;
	}

	public IWeapon getMainWeapon()
	{
		return mainWeapon;
	}
}
