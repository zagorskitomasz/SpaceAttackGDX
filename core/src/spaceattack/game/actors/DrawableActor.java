package spaceattack.game.actors;

import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.ITexture;

public abstract class DrawableActor implements IGameActor {

    protected abstract ITexture getTexture();

    private IActor actor;

    @Override
    public void setActor(final IActor actor) {

        this.actor = actor;
    }

    @Override
    public IActor getActor() {

        return actor;
    }

    @Override
    public void draw(final IBatch batch, final float alpha) {

        ITexture texture = getTexture();
        if (texture != null) {
            batch.draw(texture, getDrawingX(), getDrawingY(), texture.getWidth(), texture.getHeight());
        }
    }

    public float getDrawingX() {

        return actor.getX() - getTexture().getWidth() * 0.5f;
    }

    public float getDrawingY() {

        return actor.getY() - getTexture().getHeight() * 0.5f;
    }

    public float getX() {

        return actor.getX();
    }

    public float getY() {

        return actor.getY();
    }

    public void setX(final float x) {

        actor.setX(x);
    }

    public void setY(final float y) {

        actor.setY(y);
    }
}
