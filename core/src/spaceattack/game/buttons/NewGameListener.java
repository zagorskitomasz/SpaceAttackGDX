package spaceattack.game.buttons;

import spaceattack.consts.UIStrings;
import spaceattack.game.GameProgress;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.GameLoader;
import spaceattack.game.utils.IUtils;

public class NewGameListener extends ChangeStageButtonListener {

    private GameLoader loader;
    private IUtils utils;

    public NewGameListener(IGameStage stage, GameLoader gameLoader) {

        super(stage, Stages.MISSIONS);
        loader = gameLoader;
        utils = Factories.getUtilsFactory().create();
    }

    @Override
    public void clicked() {

        if (!loader.fileExists())
            finalizeStage();
        else
            confirm();
    }

    @Override
    public void finalizeStage() {

        stage.setResult(createResult(new GameProgress()));
    }

    private void confirm() {

        utils.confirmDialog(UIStrings.NEW_GAME, UIStrings.NEW_GAME_QUESTION, stage.getStage(),
                this::processOverrideDialogResult);
    }

    public void processOverrideDialogResult(boolean result) {

        if (result)
            finalizeStage();
    }
}