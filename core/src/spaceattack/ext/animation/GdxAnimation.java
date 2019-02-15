package spaceattack.ext.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import spaceattack.ext.texture.GdxTextureReg;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.ITexture;

public class GdxAnimation implements IAnimation
{
	private Animation<TextureRegion> gdxAnimation;
	float elapsed = 0;

	public GdxAnimation(String path,boolean loop,float fps)
	{
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(path));
		gdxAnimation = new Animation<>(1 / fps, atlas.getRegions(), loop ? PlayMode.LOOP : PlayMode.NORMAL);
	}

	@Override
	public ITexture getFrame()
	{
		this.elapsed += Gdx.graphics.getDeltaTime();

		TextureRegion textureRegion = gdxAnimation.getKeyFrame(elapsed);

		return new GdxTextureReg(textureRegion);
	}

	@Override
	public boolean isCompleted()
	{
		return gdxAnimation.isAnimationFinished(elapsed);
	}
}
