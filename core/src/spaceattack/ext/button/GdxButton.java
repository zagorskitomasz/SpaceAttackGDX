package spaceattack.ext.button;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.actors.IActor;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.IListener;

public class GdxButton extends TextButton implements IButton {

    private int columnPosition;

    public GdxButton(String text) {

        super(text, ExtUtilsFactory.INSTANCE.getGdxUtils().getUiSkin(), Consts.DEFAULT);
        getLabel().setFontScale(Sizes.X_FACTOR, Sizes.Y_FACTOR);
    }

    public GdxButton(String text, String styleName) {

        super(text, ExtUtilsFactory.INSTANCE.getGdxUtils().getUiSkin(), styleName);
    }

    @Override
    public IActor getActor() {

        return this;
    }

    @Override
    public void addListener(IListener listener) {

        addListener(new ListenerProxy(listener));
    }

    @Override
    public void setEnabled(boolean enabled) {

        setTouchable(enabled ? Touchable.enabled : Touchable.disabled);
        setDisabled(!enabled);
    }

    @Override
    public void setColumnPosition(int position) {

        columnPosition = position;
    }

    @Override
    public void draw(Batch batch, float alpha) {

        getText();
        super.draw(batch, alpha);
    }

    @Override
    public int getGridPosition() {

        return columnPosition;
    }
}
