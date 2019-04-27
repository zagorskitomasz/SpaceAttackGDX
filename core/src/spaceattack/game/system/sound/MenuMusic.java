package spaceattack.game.system.sound;

public enum MenuMusic implements Song {
    MENU("music/menu.mp3");

    MenuMusic(String path) {

        this.path = path;
    }

    private String path;

    @Override
    public String getPath() {

        return path;
    }
}
