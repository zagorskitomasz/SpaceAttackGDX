package spaceattack.ext.texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import spaceattack.consts.Sizes;
import spaceattack.game.system.graphics.ITexture;

public class GdxTexture extends Texture implements ITexture,IRegionHolder
{
	private TextureRegion textureRegion;

	public GdxTexture(String internalPath)
	{
		super(internalPath);
		textureRegion = new TextureRegion(this);
	}

	@Override
	public TextureRegion getTextureRegion()
	{
		return textureRegion;
	}

	@Override
	public int getWidth()
	{
		return Math.round(super.getWidth() * Sizes.X_FACTOR);
	}

	@Override
	public int getHeight()
	{
		return Math.round(super.getHeight() * Sizes.Y_FACTOR);
	}
}
