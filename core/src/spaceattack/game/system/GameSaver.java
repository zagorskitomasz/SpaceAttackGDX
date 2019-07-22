package spaceattack.game.system;

import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import spaceattack.consts.Paths;
import spaceattack.game.GameProgress;
import spaceattack.game.utils.IUtils;

public class GameSaver {

    private IUtils utils;
    private IFileHandle file;

    private final Lock lock;

    GameSaver() {

        lock = new ReentrantLock();
    }

    void setUtils(final IUtils utils) {

        this.utils = utils;
    }

    public void save(final GameProgress progress, final String slotIndex) {

        try {
            lock.lock();

            Integer.parseInt(slotIndex);

            loadFromFilesystem();

            SaveFile save = utils.streamToObject(SaveFile.class, readData());
            save.getSavedProgress().put(slotIndex, progress);

            String content = utils.objectToString(save, SaveFile.class);
            writeToFile(content);
        }
        catch (NumberFormatException ex) {
            System.err.println("Invalid slot index: " + slotIndex);
        }
        finally {
            lock.unlock();
        }
    }

    private void loadFromFilesystem() {

        file = utils.loadFile(Paths.SAVE);
    }

    public void writeToFile(final String fileContent) {

        file.writeString(fileContent, false);
    }

    private InputStream readData() {

        if (file == null) {
            loadFromFilesystem();
        }

        return file.read();
    }
}
