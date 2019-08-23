package spaceattack.game.system.sound;

import java.util.HashMap;
import java.util.Map;

import spaceattack.game.system.Acts;

public enum MusicPlayer {
    INSTANCE;

    private PlayableMusic introMusic;
    private PlayableMusic menuMusic;
    private Map<Acts, PlayableMusic> actMusic;

    private MusicPlayer() {

        introMusic = new PlayableMusic(IntroMusic.values(), null, this::updateIntroMusic);
        menuMusic = new PlayableMusic(MenuMusic.values(), null, this::updateMenuMusic);

        actMusic = new HashMap<>();
        actMusic.put(Acts.I, new PlayableMusic(Acts.I, Act1Music.values(), null, this::updateActMusic));
        actMusic.put(Acts.II, new PlayableMusic(Acts.II, Act2Music.values(), null, this::updateActMusic));
        actMusic.put(Acts.III, new PlayableMusic(Acts.III, Act3Music.values(), null, this::updateActMusic));
        actMusic.put(Acts.IV, new PlayableMusic(Acts.IV, Act4Music.values(), null, this::updateActMusic));
        actMusic.put(Acts.V, new PlayableMusic(Acts.V, Act5Music.values(), null, this::updateActMusic));
    }

    public void updateIntroMusic(final PlayableMusic song) {

        introMusic = song;
    }

    public void updateMenuMusic(final PlayableMusic song) {

        menuMusic = song;
    }

    public void updateActMusic(final Acts act, final PlayableMusic song) {

        actMusic.put(act, song);
    }

    public void playIntro() {

        if (introMusic.isPlaying()) {
            return;
        }

        actMusic.values().forEach(music -> music.fadeOut());
        introMusic.fadeOut();
        menuMusic.fadeOut();
        introMusic.fadeIn();
    }

    public void playMenu() {

        if (menuMusic.isPlaying()) {
            return;
        }

        actMusic.values().forEach(music -> music.fadeOut());
        introMusic.fadeOut();
        menuMusic.fadeOut();
        menuMusic.fadeIn();
    }

    public void playAct(final Acts act) {

        if (!actMusic.containsKey(act)) {
            return;
        }

        actMusic.values().forEach(music -> music.fadeOut());
        introMusic.fadeOut();
        menuMusic.fadeOut();
        actMusic.get(act).fadeIn();
    }
}
