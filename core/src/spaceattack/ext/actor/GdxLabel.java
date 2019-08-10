package spaceattack.ext.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import spaceattack.consts.Sizes;
import spaceattack.ext.batch.BatchProxyHolder;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.ILabel;
import spaceattack.game.batch.IBatch;

public class GdxLabel extends Label implements ILabel {

    private IGameActor gameActor;
    private boolean draw;

    public GdxLabel(final CharSequence text, final LabelStyle style) {

        super(text, style);
        setFontScale(Sizes.X_FACTOR, Sizes.Y_FACTOR);
    }

    @Override
    public void setGameActor(final IGameActor actor) {

        gameActor = actor;
    }

    @Override
    public void draw(final IBatch batch, final float alpha) {

        batch.draw(this, alpha);
    }

    @Override
    public void disableDrawing() {

        draw = false;
    }

    @Override
    public void enableDawing() {

        draw = true;
    }

    @Override
    public void draw(final Batch batch, final float alpha) {

        if (gameActor == null || draw) {
            super.draw(batch, alpha);

            if (draw) {
                gameActor.draw(BatchProxyHolder.INSTANCE.get(), alpha);
            }
        }
    }

    @Override
    public void update(final String text) {

        setText(text);
        pack();
        setX((Sizes.GAME_WIDTH - getWidth()) * 0.5f);
    }
}
