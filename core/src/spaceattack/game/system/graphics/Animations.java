package spaceattack.game.system.graphics;

import spaceattack.game.factories.Factories;

public enum Animations {
    // @formatter:off
    FIGHTER_EX(false, 10),
    BOSS_EX(false, 10),
    MISSILE_EX(false, 10),
    TANK_EX(false, 10),
    FIRE(true, 10),
    SHIELD(true, 10),
    TIME_WAVE(false, 25),
    TIME_FREEZE(true, 10),
    FLAME_P(true, 10),
    FLAME_E(true, 10);
    // @formatter:on

    private boolean loop;
    private float fps;

    private static boolean isTest;

    Animations(final boolean loop, final float fps) {

        this.loop = loop;
        this.fps = fps;
    }

    /**
     * Only for JUnit test purposes! This fake method won't load any real textures.
     * It will only suppress TextureNotLoadedException. After fake load
     * Textures#getTexture() method will return null!
     */
    public static void loadForTest() {

        isTest = true;
    }

    public IAnimation getAnimation() {

        if (isTest) {
            return null;
        }

        if (loop) {
            return Factories.getAnimationFactory().createLooping(this, fps);
        }

        return Factories.getAnimationFactory().create(this, fps);
    }
}
