package spaceattack.game.system.sound;

public enum Act5Music implements Song {
    SONG_1("music/act5a.mp3"), //
    SONG_2("music/act5b.mp3"), //
    SONG_3("music/act5c.mp3"), //
    SONG_4("music/act5d.mp3"), //
    SONG_5("music/act5e.mp3");

    Act5Music(final String path) {

        this.path = path;
    }

    private String path;

    @Override
    public String getPath() {

        return path;
    }
}
