package spaceattack.game.buttons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spaceattack.consts.UIStrings;
import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.stages.IInputListener;
import spaceattack.game.stages.Stages;
import spaceattack.game.stages.UIStage;
import spaceattack.game.stages.UIStage.Message;
import spaceattack.game.system.GameSaver;

public class CreatePlayerListener implements IListener, IInputListener {

    private final int buttonIndex;
    private final GameSaver saver;
    private final UIStage stage;

    private final Pattern namePattern;

    public CreatePlayerListener(final int buttonIndex, final GameSaver saver, final UIStage stage) {

        this.buttonIndex = buttonIndex;
        this.saver = saver;
        this.stage = stage;

        namePattern = Pattern.compile("[A-Za-z0-9]{1,10}");
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
            newPlayerProgress.setSlot(buttonIndex);
            saver.save(newPlayerProgress);

            StageResult result = new StageResult();
            result.setNextStage(Stages.MAIN_MENU);
            result.setGameProgress(newPlayerProgress);
            stage.setResult(result);
        }
        else {
            stage.showMessage(new Message(UIStrings.NAME_VALID_TITLE, UIStrings.NAME_VALID_TEXT));
        }
    }

    boolean validate(final String input) {

        Matcher matcher = namePattern.matcher(input);
        return matcher.matches();
    }
}
