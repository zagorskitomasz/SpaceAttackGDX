package spaceattack.ext.animation;

import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.IAnimationFactory;

public enum ExtAnimationFactory implements IAnimationFactory {
    INSTANCE;

    @Override
    public IAnimation create(final Animations animation, final float fps) {

        return new GdxAnimation(animation, false, fps);
    }

    @Override
    public IAnimation createLooping(final Animations animation, final float fps) {

        return new GdxAnimation(animation, true, fps);
    }
}
