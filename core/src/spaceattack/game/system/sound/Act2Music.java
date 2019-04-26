package spaceattack.game.system.sound;

public enum Act2Music implements Song {
    SONG_1("music/act2a.mp3"), //
    SONG_2("music/act2b.mp3"), //
    SONG_3("music/act2c.mp3"), //
    SONG_4("music/act2d.mp3"), //
    SONG_5("music/act2e.mp3");

    Act2Music(String path) {

        this.path = path;
    }

    private String path;

    @Override
    public String getPath() {

        return path;
    }
}
