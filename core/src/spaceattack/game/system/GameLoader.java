package spaceattack.game.system;

import static java.util.stream.Collectors.toMap;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import spaceattack.consts.Paths;
import spaceattack.game.GameProgress;
import spaceattack.game.utils.IUtils;

public class GameLoader {

    private IUtils utils;
    private IFileHandle file;

    private final Lock lock;

    GameLoader() {

        lock = new ReentrantLock();
    }

    void setUtils(final IUtils utils) {

        this.utils = utils;
    }

    public GameProgress load(final String slotIndex) {

        try {
            lock.lock();

            loadFromFilesystem();

            if (fileExists()) {
                return parse(slotIndex);
            }

            return null;
        }
        finally {
            lock.unlock();
        }
    }

    private GameProgress parse(final String slotIndex) {

        SaveFile save = readFile();

        GameProgress progress = save.getProgress(slotIndex);

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

    public Map<String, String> loadAll() {

        try {
            lock.lock();

            loadFromFilesystem();

            if (fileExists()) {
                return parseAll();
            }

            return null;
        }
        finally {
            lock.unlock();
        }
    }

    private Map<String, String> parseAll() {

        try {
            SaveFile save = readFile();

            return save //
                    .getSavedProgress() //
                    .entrySet() //
                    .stream() //
                    .collect(
                            toMap(entry -> {
                                return entry.getKey().toString();
                            }, entry -> {
                                return entry.getValue().getPlayerName();
                            }));
        }
        catch (Exception lvEx) {
            return Collections.emptyMap();
        }
    }

    protected SaveFile readFile() {

        InputStream fileContent = readData();

        return utils.streamToObject(SaveFile.class, fileContent);
    }
}
