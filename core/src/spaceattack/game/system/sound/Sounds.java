package spaceattack.game.system.sound;

import java.util.Arrays;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.ResourceNotLoadedException;

public enum Sounds {
    // shots
    RED_LASER("sound/redShot.mp3"), //
    ROCKET_MISSILE("sound/missileFly.mp3"), //
    MINE("sound/mine.mp3"), //
    TURBO_LASER("sound/greenShot.mp3"), //
    SHIELD("sound/shield.mp3"), //
    FLAME("sound/flame.mp3"), //
    POWER_UP("sound/powerUp.mp3"), //

    // explosions
    SMALL_SHIP_EXPLOSION("sound/exShip.mp3"), //
    BOSS_EXPLOSION("sound/exBoss.mp3"), //
    MISSILE_EXPLOSION("sound/exMissile.mp3"), //
    TIME_WAVE("sound/timeWave.mp3");

    private static boolean isTest;

    private String path;
    private ISound sound;

    Sounds(final String path) {

        this.path = path;
    }

    public static void load() {

        for (Sounds sound : values()) {
            sound.sound = Factories.getSoundFactory().create(sound.path);
        }
    }

    public void play() {

        if (sound == null && !isTest) {
            throw new ResourceNotLoadedException(name());
        }

        if (sound != null) {
            sound.play();
        }
    }

    public void stop() {

        if (sound == null && !isTest) {
            throw new ResourceNotLoadedException(name());
        }

        if (sound != null) {
            sound.stop();
        }
    }

    public void stop(final long instanceId) {

        if (sound == null && !isTest) {
            throw new ResourceNotLoadedException(name());
        }

        if (sound != null) {
            sound.stop(instanceId);
        }
    }

    public long loop() {

        if (sound == null && !isTest) {
            throw new ResourceNotLoadedException(name());
        }

        if (sound != null) {
            return sound.loop();
        }
        return 0;
    }

    /**
     * Only for JUnit test purposes! This fake method won't load any real textures.
     * It will only suppress TextureNotLoadedException. After fake load
     * Textures#getTexture() method will return null!
     */
    public static void loadForTest() {

        isTest = true;
    }

    public static void stopAll() {

        Arrays.asList(values()).forEach(sound -> sound.stop());
    }
}
