package spaceattack.game.buttons;

import spaceattack.consts.UIStrings;
import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.IInputListener;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.GameSaver;

public class CreatePlayerListener implements IListener, IInputListener {

    private final int buttonIndex;
    private final GameSaver saver;
    private final IGameStage stage;

    public CreatePlayerListener(final int buttonIndex, final GameSaver saver, final IGameStage stage) {

        this.buttonIndex = buttonIndex;
        this.saver = saver;
        this.stage = stage;
    }

    @Override
    public void clicked() {

        stage.getStage().setTextInputListener(this);
        stage.getStage().askPlayer(UIStrings.ENTER_YOUR_NAME_TITLE, UIStrings.ENTER_YOUR_NAME);
    }

    @Override
    public void process(final String input) {

        if (validate(input)) {
            GameProgress newPlayerProgress = new GameProgress();
            newPlayerProgress.setPlayerName(input);
            saver.save(newPlayerProgress, String.valueOf(buttonIndex));

            StageResult result = new StageResult();
            result.setNextStage(Stages.MISSIONS);
            result.setGameProgress(newPlayerProgress);
            stage.setResult(result);
        }
    }

    private boolean validate(final String input) {

        return true; // TODO
    }
}
