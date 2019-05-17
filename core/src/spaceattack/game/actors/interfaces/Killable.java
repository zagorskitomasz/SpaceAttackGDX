package spaceattack.game.actors.interfaces;

import spaceattack.consts.Sizes;

public interface Killable {

    float downMargin = 100;
    float margin = downMargin + 600 * Sizes.Y_FACTOR;

    void setToKill();

    boolean isToKill();

    default void disappearIfNeeded() {

        if (isOutOfScreen()) {
            setToKill();
        }
    }

    default boolean isOutOfScreen() {

        return getX() < 0 - margin || getX() > Sizes.GAME_WIDTH + margin || getY() < 0 - downMargin
                || getY() > Sizes.GAME_HEIGHT + margin;
    }

    float getX();

    float getY();
}