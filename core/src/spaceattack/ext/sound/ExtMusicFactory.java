package spaceattack.ext.sound;

import spaceattack.game.system.sound.IMusic;
import spaceattack.game.system.sound.IMusicFactory;

public enum ExtMusicFactory implements IMusicFactory {
    INSTANCE;

    @Override
    public IMusic create(String path) {

        return new GdxMusic(path);
    }
}
