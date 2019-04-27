package spaceattack.ext.texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import spaceattack.consts.Sizes;
import spaceattack.game.system.graphics.ITexture;

public class GdxTextureReg implements ITexture, IRegionHolder {

    private TextureRegion region;

    public GdxTextureReg(TextureRegion textureRegion) {

        region = textureRegion;
    }

    @Override
    public int getWidth() {

        return Math.round(region.getRegionWidth() * Sizes.X_FACTOR);
    }

    @Override
    public int getHeight() {

        return Math.round(region.getRegionHeight() * Sizes.Y_FACTOR);
    }

    @Override
    public TextureRegion getTextureRegion() {

        return region;
    }
}
