package spaceattack.game.system;

import java.util.HashMap;
import java.util.Map;

import spaceattack.game.GameProgress;

public class SaveFile {

    private Map<String, GameProgress> savedProgress;

    public SaveFile() {

        savedProgress = new HashMap<>();
    }

    public Map<String, GameProgress> getSavedProgress() {

        return savedProgress;
    }

    public void setSavedProgress(final Map<String, GameProgress> savedProgress) {

        this.savedProgress = savedProgress;
    }

    public GameProgress getProgress(final String playerName) {

        return savedProgress.get(playerName);
    }
}
