package spaceattack.ext.texture;

import com.badlogic.gdx.graphics.Texture;

import spaceattack.game.system.graphics.ITexture;

class GdxTexture extends Texture implements ITexture
{
	public GdxTexture(String internalPath)
	{
		super(internalPath);
	}
}
