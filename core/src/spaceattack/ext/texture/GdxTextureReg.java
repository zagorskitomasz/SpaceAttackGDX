package spaceattack.ext.texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import spaceattack.consts.Sizes;
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
		return Math.round(getRegionWidth() * Sizes.X_FACTOR);
	}

	@Override
	public int getHeight()
	{
		return Math.round(getRegionHeight() * Sizes.Y_FACTOR);
	}

	@Override
	public void dispose()
	{
		getTexture().dispose();
	}

	@Override
	public TextureRegion getTextureRegion()
	{
		return this;
	}
}
