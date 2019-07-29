package spaceattack.game.buttons;

import spaceattack.consts.UIStrings;
import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.stages.impl.MainMenuStage;
import spaceattack.game.system.GameSaver;

public class DeletePlayerListener implements IListener {

    private final int buttonIndex;
    private final GameSaver saver;
    private final IGameStage stage;

    public DeletePlayerListener(final int buttonIndex, final GameSaver saver, final MainMenuStage stage) {

        this.buttonIndex = buttonIndex;
        this.saver = saver;
        this.stage = stage;
    }

    @Override
    public void clicked() {

        Factories.getUtilsFactory().create().confirmDialog(UIStrings.EXIT, UIStrings.DELETE_PLAYER_QUESTION,
                stage.getStage(), this::process);
    }

    public void process(final boolean result) {

        if (result) {
            saver.delete(buttonIndex);
            StageResult stageResult = new StageResult();
            stageResult.setNextStage(Stages.PLAYERS_MENU);
            stageResult.setGameProgress(new GameProgress());
            stage.setResult(stageResult);
        }
    }
}
