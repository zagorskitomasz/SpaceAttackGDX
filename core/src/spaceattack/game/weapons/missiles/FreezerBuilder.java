package spaceattack.game.weapons.missiles;

import spaceattack.game.actors.interfaces.Freezable;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.Animations;

public enum FreezerBuilder {
    INSTANCE;

    public Freezer build(final Freezable freezable, final float duration) {

        Freezer freezer = new Freezer();
        freezer.setFreezeAnimation(Animations.TIME_FREEZE.getAnimation());
        freezer.setFreezeController(new FrameController(Factories.getUtilsFactory().create(), 1000f / duration));
        freezer.setFreezable(freezable);

        return freezer;
    }
}
