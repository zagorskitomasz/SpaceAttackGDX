package spaceattack.game.system.sound;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.badlogic.gdx.math.MathUtils;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.Acts;

class PlayableMusic {

    private static final int MAX_VOLUME = 85;

    private final IMusic music;
    private final AtomicBoolean isFadingOut;

    public PlayableMusic(final Song[] playlist, final Song nowPlaying, final Consumer<PlayableMusic> updater) {

        isFadingOut = new AtomicBoolean(false);
        Song song = chooseCurrentSong(playlist, nowPlaying);

        music = Factories.getMusicFactory().create(song.getPath());
        updater.accept(this);

        music.setLooping(false);
        music.setOnCompletionListener(music -> {

            music.dispose();
            PlayableMusic nextMusic = new PlayableMusic(playlist, song, updater);
            nextMusic.play();
            if (isFadingOut.get()) {
                nextMusic.fadeOut();
            }
        });
    }

    public PlayableMusic(final Acts act, final Song[] playlist, final Song nowPlaying,
            final BiConsumer<Acts, PlayableMusic> updater) {

        isFadingOut = new AtomicBoolean(false);
        Song song = chooseCurrentSong(playlist, nowPlaying);

        music = Factories.getMusicFactory().create(song.getPath());
        updater.accept(act, this);

        music.setLooping(false);
        music.setOnCompletionListener(music -> {

            music.dispose();
            PlayableMusic nextMusic = new PlayableMusic(act, playlist, song, updater);
            nextMusic.play();
            if (isFadingOut.get()) {
                nextMusic.fadeOut();
            }
        });
    }

    private void play() {

        music.setVolume(MAX_VOLUME);
        music.play();
    }

    private Song chooseCurrentSong(final Song[] playlist, final Song nowPlaying) {

        if (playlist.length == 1) {
            return playlist[0];
        }
        else {
            return chooseSong(playlist, nowPlaying);
        }
    }

    private Song chooseSong(final Song[] playlist, final Song nowPlaying) {

        Song nextSong = null;
        do {
            int index = MathUtils.random(playlist.length - 1);
            nextSong = playlist[index];
        }
        while (nowPlaying != null && nowPlaying.equals(nextSong));

        return nextSong;
    }

    public void fadeIn() {

        new Thread(() -> {
            if (isFadingOut.get()) {
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            music.setVolume(0);
            music.play();

            for (float i = 1; i <= MAX_VOLUME; i++) {
                music.setVolume(i / 100);
                try {
                    Thread.sleep(20);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void fadeOut() {

        if (music == null || !music.isPlaying()) {
            return;
        }

        new Thread(() -> {
            isFadingOut.set(true);
            for (float i = MAX_VOLUME; i >= 0; i--) {
                music.setVolume(i / 100);
                try {
                    Thread.sleep(20);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isFadingOut.set(false);
            music.pause();

        }).start();
    }

    public boolean isPlaying() {

        return music.isPlaying();
    }
}