package spaceattack.game.system.sound;

public enum Act4Music implements Song {
    SONG_1("music/act4a.mp3"), //
    SONG_2("music/act4b.mp3"), //
    SONG_3("music/act4c.mp3"), //
    SONG_4("music/act4d.mp3"), //
    SONG_5("music/act4e.mp3");

    Act4Music(final String path) {

        this.path = path;
    }

    private String path;

    @Override
    public String getPath() {

        return path;
    }
}
