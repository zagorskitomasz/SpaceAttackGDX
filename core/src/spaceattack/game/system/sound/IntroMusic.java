package spaceattack.game.system.sound;

public enum IntroMusic implements Song {
    INTRO("music/intro.mp3");

    IntroMusic(final String path) {

        this.path = path;
    }

    private String path;

    @Override
    public String getPath() {

        return path;
    }
}
