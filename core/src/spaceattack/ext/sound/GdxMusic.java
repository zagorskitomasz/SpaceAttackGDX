package spaceattack.ext.sound;

import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import spaceattack.game.system.sound.IMusic;

public class GdxMusic implements IMusic {

    private Music music;

    public GdxMusic(String path) {

        music = Gdx.audio.newMusic(Gdx.files.internal(path));
    }

    @Override
    public void play() {

        music.play();
    }

    @Override
    public void pause() {

        music.pause();
    }

    @Override
    public void stop() {

        music.stop();
    }

    @Override
    public boolean isPlaying() {

        return music.isPlaying();
    }

    @Override
    public void setLooping(boolean isLooping) {

        music.setLooping(isLooping);
    }

    @Override
    public boolean isLooping() {

        return music.isLooping();
    }

    @Override
    public void setVolume(float volume) {

        try {
            music.setVolume(volume);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public float getVolume() {

        return music.getVolume();
    }

    @Override
    public void setPan(float pan, float volume) {

        music.setPan(pan, volume);
    }

    @Override
    public void setPosition(float position) {

        music.setPosition(position);
    }

    @Override
    public float getPosition() {

        return music.getPosition();
    }

    @Override
    public void dispose() {

        music.dispose();
    }

    @Override
    public void setOnCompletionListener(Consumer<IMusic> listener) {

        music.setOnCompletionListener(music -> listener.accept(this));
    }
}