package spaceattack.game;

import spaceattack.game.stages.Stages;

public class StageResult {

    private Stages nextStage;
    private GameProgress gameProgress;

    public StageResult() {

        nextStage = Stages.PLAYERS_MENU;
    }

    public Stages getNextStage() {

        return nextStage;
    }

    public void setNextStage(final Stages nextStage) {

        this.nextStage = nextStage;
    }

    public GameProgress getGameProgress() {

        return gameProgress;
    }

    public void setGameProgress(final GameProgress gameProgress) {

        this.gameProgress = gameProgress;
    }

    @Override
    public boolean equals(final Object other) {

        if (other == null || !(other instanceof StageResult)) {
            return false;
        }

        StageResult otherResult = (StageResult) other;

        return otherResult.nextStage == nextStage && otherResult.gameProgress.equals(gameProgress);
    }
}
