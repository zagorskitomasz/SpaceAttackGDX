package spaceattack.ext.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import spaceattack.ext.texture.GdxTextureReg;
import spaceattack.game.ext.system.Atlases;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.ITexture;

public class GdxAnimation implements IAnimation {

    private final Animation<TextureRegion> gdxAnimation;
    float elapsed = 0;

    public GdxAnimation(final Animations animation, final boolean loop, final float fps) {

        TextureAtlas atlas = Atlases.getAtlas(animation);
        gdxAnimation = new Animation<>(1 / fps, atlas.getRegions(), loop ? PlayMode.LOOP : PlayMode.NORMAL);
    }

    @Override
    public ITexture getFrame() {

        this.elapsed += Gdx.graphics.getDeltaTime();

        TextureRegion textureRegion = gdxAnimation.getKeyFrame(elapsed);

        return new GdxTextureReg(textureRegion);
    }

    @Override
    public boolean isCompleted() {

        return gdxAnimation.isAnimationFinished(elapsed);
    }
}
