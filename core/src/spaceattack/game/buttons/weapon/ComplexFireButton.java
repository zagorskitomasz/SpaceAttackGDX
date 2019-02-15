package spaceattack.game.buttons.weapon;

import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;

@SuppressWarnings("unused")
public class ComplexFireButton extends FireButton
{
	private ITexture drawableUp;
	private ITexture drawableDown;
	private ITexture specialDrawableUp;
	private ITexture specialDrawableDown;

	private ITexture weaponIcon;

	private IWeapon mainWeapon;
	private int ammo;

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

	public void setSpecialWeapon(IWeapon weapon,int ammo,ITexture weaponIcon)
	{
		setWeapon(weapon);
		this.ammo = ammo;
		this.weaponIcon = weaponIcon;
		button.setDown(specialDrawableDown);
		button.setUp(specialDrawableUp);
	}

	@Override
	protected void fire()
	{
		super.fire();

		if (mainWeapon != getWeapon())
		{
			ammo--;

			if (ammo <= 0)
			{
				setWeapon(mainWeapon);
				button.setDown(drawableDown);
				button.setUp(drawableUp);
				weaponIcon = null;
			}
		}
	}

	@Override
	public void draw(IBatch batch,float alpha)
	{
		super.draw(batch, alpha);

		if (weaponIcon == null)
			return;

		for (int i = 0; i < ammo; i++)
		{
			batch.draw(weaponIcon, Sizes.GAME_WIDTH * 0.1f,
					Sizes.GAME_HEIGHT - Sizes.GAME_HEIGHT * 0.15f - (i * weaponIcon.getHeight() * 0.5f));
		}
	}
}
