package spaceattack.ext.texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import spaceattack.game.system.graphics.ITexture;

public class GdxTextureReg extends TextureRegion implements ITexture,IRegionHolder
{
	public GdxTextureReg(TextureRegion textureRegion)
	{
		super(textureRegion);
	}

	@Override
	public int getWidth()
	{
		return getRegionWidth();
	}

	@Override
	public int getHeight()
	{
		return getRegionHeight();
	}

	@Override
	public TextureRegion getTextureRegion()
	{
		return this;
	}
}
