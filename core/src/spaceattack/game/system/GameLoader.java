package spaceattack.game.system;

import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import spaceattack.consts.Paths;
import spaceattack.game.GameProgress;
import spaceattack.game.utils.IUtils;

public class GameLoader {

    private IUtils utils;
    private IFileHandle file;

    private final Lock lock;

    private GameSaver saver;

    GameLoader() {

        lock = new ReentrantLock();
    }

    void setUtils(final IUtils utils) {

        this.utils = utils;
    }

    void setSaver(final GameSaver saver) {

        this.saver = saver;
    }

    public GameProgress load(final String playerName) {

        try {
            lock.lock();

            loadFromFilesystem();

            if (fileExists()) {
                return parse(playerName);
            }

            return null;
        }
        finally {
            lock.unlock();
        }
    }

    private GameProgress parse(final String playerName) {

        InputStream fileContent = readData();

        SaveFile save = utils.streamToObject(SaveFile.class, fileContent);

        GameProgress progress = save.getProgress(playerName);

        if (progress == null) {
            progress = new GameProgress();
            progress.setPlayerName(playerName);
            saver.save(progress);
        }
        return progress;
    }

    private void loadFromFilesystem() {

        file = utils.loadFile(Paths.SAVE);
    }

    public boolean fileExists() {

        try {
            lock.lock();

            if (file == null) {
                loadFromFilesystem();
            }

            return file.exists();
        }
        finally {
            lock.unlock();
        }
    }

    private InputStream readData() {

        if (file == null) {
            loadFromFilesystem();
        }

        return file.read();
    }
}
