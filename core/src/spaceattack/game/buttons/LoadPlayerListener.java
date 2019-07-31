package spaceattack.game.buttons;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.GameLoader;

public class LoadPlayerListener implements IListener {

    private final int buttonIndex;
    private final GameLoader loader;
    private final IGameStage stage;

    public LoadPlayerListener(final int buttonIndex, final GameLoader loader, final IGameStage stage) {

        this.buttonIndex = buttonIndex;
        this.loader = loader;
        this.stage = stage;
    }

    @Override
    public void clicked() {

        GameProgress loadedPlayerProgress = loader.load(String.valueOf(buttonIndex));

        StageResult result = new StageResult();
        result.setNextStage(Stages.MISSIONS);
        result.setGameProgress(loadedPlayerProgress);
        stage.setResult(result);
    }

}
