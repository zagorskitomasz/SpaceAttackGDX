package spaceattack.game.system.sound;

import java.util.function.Consumer;

public interface IMusic {

    void setOnCompletionListener(Consumer<IMusic> listener);

    void play();

    void pause();

    void stop();

    boolean isPlaying();

    void setLooping(boolean isLooping);

    boolean isLooping();

    void setVolume(float volume);

    float getVolume();

    void setPan(float pan, float volume);

    void setPosition(float position);

    float getPosition();

    void dispose();
}
