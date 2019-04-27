package spaceattack.game.buttons;

import spaceattack.game.buttons.IListener;
import spaceattack.game.stages.impl.MissionsStage;

public class ActChangeButtonListener implements IListener {

    public enum Variants {
        NEXT, PREV;
    }

    private MissionsStage stage;
    private Variants variant;

    public ActChangeButtonListener(MissionsStage stage, Variants variant) {

        this.stage = stage;
        this.variant = variant;
    }

    @Override
    public void clicked() {

        switch (variant) {
        case NEXT:
            stage.nextAct();
            break;
        case PREV:
            stage.previousAct();
            break;
        }
    }
}
