package spaceattack.game.stages;

import spaceattack.game.StageResult;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;

public class IntroStage extends GameplayStage {

    private IUtils utils;
    private int touched;

    @Override
    public IInputProcessor getInputProcessor() {

        return null;
    }

    public void setUtils(final IUtils utils) {

        this.utils = utils;
    }

    @Override
    public void act(final float delta) {

        super.act(delta);

        if (utils.getTouch() != null) {
            touched++;
            if (touched > 10) {
                finalizeStage();
            }
        }
    }

    @Override
    public void finalizeStage() {

        Sounds.stopAll();
        StageResult result = new StageResult();
        result.setNextStage(Stages.PLAYERS_MENU);
        result.setGameProgress(null);
        setResult(result);
    }
}
