package spaceattack.game.system;

import java.util.HashMap;

import spaceattack.game.GameProgress;

public class SaveFile {

    private HashMap<String, GameProgress> savedProgress;

    public SaveFile() {

        savedProgress = new HashMap<>();
    }

    public HashMap<String, GameProgress> getSavedProgress() {

        return savedProgress;
    }

    public void setSavedProgress(final HashMap<String, GameProgress> savedProgress) {

        this.savedProgress = savedProgress;
    }

    public GameProgress getProgress(final String slotIndex) {

        return savedProgress.get(slotIndex);
    }
}
