package spaceattack.game.actors.interfaces;

import spaceattack.game.weapons.missiles.Freezer;

public interface Freezable {

    void freeze(Freezer freezer);

    void unfreeze();

    float getWidth();

    float getHeight();

    float getDrawingX();

    float getDrawingY();
}
