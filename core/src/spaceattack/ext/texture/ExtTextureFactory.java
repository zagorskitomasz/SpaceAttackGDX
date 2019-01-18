package spaceattack.ext.texture;

import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.ITextureFactory;

public enum ExtTextureFactory implements ITextureFactory
{
	INSTANCE;

	@Override
	public ITexture create(String path)
	{
		return new GdxTexture(path);
	}
}
