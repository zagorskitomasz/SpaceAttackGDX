package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;

public interface IButton extends IGameActor, IActor {

    @Override
    default void setActor(final IActor actor) {

        // do nothing
    }

    @Override
    default void draw(final IBatch batch, final float alpha) {

        // do nothing
    }

    @Override
    void setPosition(float x, float y);

    void setSize(float buttonWidth, float buttonHeight);

    void addListener(IListener listener);

    void setEnabled(boolean enabled);

    void setDisabledStyle(boolean enabled);

    void setVisible(boolean visible);

    void setColumnPosition(int position);

    int getGridPosition();

    void setText(String apply);
}
