package spaceattack.game.buttons.weapon;

import spaceattack.consts.Sizes;
import spaceattack.game.buttons.IImageButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;

public enum FireButtonsBuilder
{
	INSTANCE;

	public FireButton primary(IWeapon weapon)
	{
		ITexture textureUp = Textures.RED_BUTTON_UP.getTexture();
		ITexture textureDown = Textures.RED_BUTTON_DOWN.getTexture();
		ITexture textureDisabled = Textures.FIRE_BUTTON_DISABLED.getTexture();

		IImageButton imageButton = Factories.getImageButtonsFactory().create(textureUp, textureDown, textureDisabled);

		FireButton button = new FireButton();
		button.setImageButton(imageButton);
		button.setWeapon(weapon);
		button.setPosition(Sizes.GAME_WIDTH * 0.54f - imageButton.getWidth() * 0.5f, Sizes.GAME_HEIGHT * 0.02f);

		return button;
	}

	public ComplexFireButton secondary(IWeaponController controller,IWeapon weapon)
	{
		ITexture textureUp = Textures.GREEN_BUTTON_UP.getTexture();
		ITexture textureDown = Textures.GREEN_BUTTON_DOWN.getTexture();
		ITexture textureDisabled = Textures.FIRE_BUTTON_DISABLED.getTexture();

		IImageButton imageButton = Factories.getImageButtonsFactory().create(textureUp, textureDown, textureDisabled);

		ComplexFireButton button = new ComplexFireButton();
		button.setImageButton(imageButton);
		button.setMainWeapon(weapon);
		button.setPosition(Sizes.GAME_WIDTH * 0.82f - imageButton.getWidth() * 0.5f, Sizes.GAME_HEIGHT * 0.02f);
		button.setWeaponController(controller);

		return button;
	}
}
