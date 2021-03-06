package spaceattack.game.actors;

import spaceattack.consts.Consts;
import spaceattack.game.batch.IBatch;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.IUtils;

public class TimeLabel implements IGameActor {

    private IUtils utils;
    private ILabel label;
    private long showed;

    public TimeLabel() {

        showed = 0;
        utils = Factories.getUtilsFactory().create();
    }

    @Override
    public void draw(IBatch batch, float alpha) {

        if (!isVisible())
            label.disableDrawing();
    }

    public void show() {

        label.enableDawing();
        showed = utils.millis();
    }

    public boolean isVisible() {

        return showed + Consts.Gameplay.LABEL_SHOW_TIME > utils.millis();
    }

    @Override
    public IActor getActor() {

        return label;
    }

    public void setLabel(ILabel label) {

        this.label = label;
        label.setGameActor(this);
    }

    @Override
    public void setActor(IActor label) {

        // do nothing
    }

    @Override
    public void act(float delta) {

        // do nothing
    }
}
