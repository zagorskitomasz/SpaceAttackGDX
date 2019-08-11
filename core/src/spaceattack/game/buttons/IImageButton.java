package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;
import spaceattack.game.input.InputType;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;

public interface IImageButton extends IActor, IGameActor {

    @Override
    default void setActor(final IActor actor) {

        // do nothing
    }

    @Override
    default void draw(final IBatch batch, final float alpha) {

        // do nothing
    }

    void fire(InputType type);

    boolean isPressed();

    IVector screenToStageCoordinates(IVector touch);

    boolean isDisabled();

    void setEnabled(boolean b);

    void setDown(ITexture texture);

    void setUp(ITexture texture);

    void setGameActor(IGameActor gameActor);

    void setAction(Runnable action);

    IGameActor getGameActor();
}
