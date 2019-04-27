package spaceattack.game.system.sound;

public enum Act1Music implements Song {
    SONG_1("music/act1a.mp3"), //
    SONG_2("music/act1b.mp3"), //
    SONG_3("music/act1c.mp3"), //
    SONG_4("music/act1d.mp3"), //
    SONG_5("music/act1e.mp3");

    Act1Music(String path) {

        this.path = path;
    }

    private String path;

    @Override
    public String getPath() {

        return path;
    }
}
