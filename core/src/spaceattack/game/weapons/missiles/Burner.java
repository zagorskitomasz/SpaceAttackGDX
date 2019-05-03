package spaceattack.game.weapons.missiles;

import spaceattack.game.actors.interfaces.Ignitable;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.ITexture;

public class Burner {

    private Ignitable ignitable;
    private IAnimation burningAnimation;
    private FrameController burningController;
    private boolean isBurning;
    private float burningDPS;

    Burner() {

        // do nothing
    }

    public void setBurningAnimation(final IAnimation animation) {

        burningAnimation = animation;
    }

    public void setBurningController(final FrameController controller) {

        burningController = controller;
    }

    public void setIgnitable(final Ignitable ignitable) {

        this.ignitable = ignitable;
    }

    public void burn(final float delta) {

        if (isBurning) {
            if (burningController.check()) {
                burningDPS = 0;
                isBurning = false;
            }
            else {
                ignitable.takeDmg(burningDPS * delta);
            }
        }
    }

    public void draw(final IBatch batch) {

        if (isBurning) {
            ITexture texture = burningAnimation.getFrame();
            batch.draw(texture,
                    ignitable.getDrawingX() + ignitable.getWidth() / 2 - texture.getWidth() / 2,
                    ignitable.getDrawingY() + ignitable.getHeight() / 2 - texture.getHeight() / 2,
                    texture.getWidth(), texture.getHeight());
        }
    }

    public void ignite(final float burningDPS, final long fireDuration) {

        if (ignitable.isImmortal()) {
            return;
        }

        if (burningDPS > this.burningDPS) {
            this.burningDPS = burningDPS;
        }

        burningController.reset(1000 / (float) fireDuration);
        isBurning = true;
    }
}
