package spaceattack.ext.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import spaceattack.game.buttons.IListener;

public class HoverListenerProxy extends InputListener {

    private final IListener listener;

    public HoverListenerProxy(final IListener listener) {

        this.listener = listener;
    }

    @Override
    public boolean mouseMoved(final InputEvent event, final float x, final float y) {

        listener.clicked();
        return true;
    }
}
