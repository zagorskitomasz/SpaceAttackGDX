package spaceattack.ext.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import spaceattack.game.system.sound.ISound;

class GdxSound implements ISound, Sound {

    private final Sound sound;

    public GdxSound(final String path) {

        sound = Gdx.audio.newSound(Gdx.files.internal(path));
    }

    @Override
    public long play() {

        return sound.play();
    }

    @Override
    public long play(final float volume) {

        return sound.play(volume);
    }

    @Override
    public long play(final float volume, final float pitch, final float pan) {

        return sound.play(volume, pitch, pan);
    }

    @Override
    public long loop() {

        return sound.loop();
    }

    @Override
    public long loop(final float volume) {

        return sound.loop(volume);
    }

    @Override
    public long loop(final float volume, final float pitch, final float pan) {

        return sound.loop(volume, pitch, pan);
    }

    @Override
    public void stop() {

        sound.stop();
    }

    @Override
    public void pause() {

        sound.pause();
    }

    @Override
    public void resume() {

        sound.resume();
    }

    @Override
    public void dispose() {

        sound.dispose();
    }

    @Override
    public void stop(final long soundId) {

        sound.stop(soundId);
    }

    @Override
    public void pause(final long soundId) {

        sound.pause(soundId);
    }

    @Override
    public void resume(final long soundId) {

        sound.resume(soundId);
    }

    @Override
    public void setLooping(final long soundId, final boolean looping) {

        sound.setLooping(soundId, looping);
    }

    @Override
    public void setPitch(final long soundId, final float pitch) {

        sound.setPitch(soundId, pitch);
    }

    @Override
    public void setVolume(final long soundId, final float volume) {

        sound.setVolume(soundId, volume);
    }

    @Override
    public void setPan(final long soundId, final float pan, final float volume) {

        sound.setPan(soundId, pan, volume);
    }
}
