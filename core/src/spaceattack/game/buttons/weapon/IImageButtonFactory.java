package spaceattack.game.buttons.weapon;

import spaceattack.game.buttons.IImageButton;
import spaceattack.game.system.graphics.ITexture;

public interface IImageButtonFactory
{
	public IImageButton create(ITexture textureUp,ITexture textureDown,ITexture textureDisabled);
}
