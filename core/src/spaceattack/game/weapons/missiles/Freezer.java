package spaceattack.game.weapons.missiles;

import spaceattack.game.actors.interfaces.Freezable;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.ITexture;

public class Freezer {

    private Freezable freezable;
    private IAnimation freezeAnimation;
    private FrameController freezeController;

    Freezer() {

        // do nothing
    }

    public void setFreezeAnimation(final IAnimation animation) {

        freezeAnimation = animation;
    }

    public void setFreezeController(final FrameController controller) {

        freezeController = controller;
        freezeController.check();
    }

    public void setFreezable(final Freezable freezable) {

        this.freezable = freezable;
    }

    public void act(final float delta) {

        if (freezeController.check()) {
            freezable.unfreeze();
        }
    }

    public void draw(final IBatch batch) {

        ITexture texture = freezeAnimation.getFrame();
        batch.draw(texture,
                freezable.getDrawingX() + freezable.getWidth() / 2 - texture.getWidth() / 2,
                freezable.getDrawingY() + freezable.getHeight() / 2 - texture.getHeight() / 2,
                texture.getWidth(), texture.getHeight());
    }
}
