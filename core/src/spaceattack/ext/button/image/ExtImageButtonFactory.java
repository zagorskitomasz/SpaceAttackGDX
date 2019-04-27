package spaceattack.ext.button.image;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import spaceattack.ext.texture.GdxTexture;
import spaceattack.game.buttons.IImageButton;
import spaceattack.game.buttons.IProgressButton;
import spaceattack.game.buttons.weapon.IImageButtonFactory;
import spaceattack.game.system.graphics.ITexture;

public enum ExtImageButtonFactory implements IImageButtonFactory {
    INSTANCE;

    @Override
    public IImageButton create(ITexture textureUp, ITexture textureDown, ITexture textureDisabled) {

        Drawable drawableUp = createDrawable(textureUp);
        Drawable drawableDown = createDrawable(textureDown);
        Drawable drawableDisabled = createDrawable(textureDisabled);

        return new GdxImageButton(drawableUp, drawableDown, drawableDisabled);
    }

    private Drawable createDrawable(ITexture texture) {

        TextureRegion region = new TextureRegion((GdxTexture) texture);
        return new TextureRegionDrawable(region);
    }

    @Override
    public IProgressButton create(ITexture background, ITexture slider) {

        Drawable drawableBackground = createDrawable(background);

        return new GdxProgressButton(drawableBackground, (GdxTexture) slider);
    }
}
