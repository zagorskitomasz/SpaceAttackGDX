package spaceattack.game.system.sound;

public enum Act2Music implements Song {
    SONG_1("music/act3a.mp3"), //
    SONG_2("music/act3b.mp3"), //
    SONG_3("music/act3c.mp3"), //
    SONG_4("music/act3d.mp3"), //
    SONG_5("music/act3e.mp3");

    Act2Music(final String path) {

        this.path = path;
    }

    private String path;

    @Override
    public String getPath() {

        return path;
    }
}
