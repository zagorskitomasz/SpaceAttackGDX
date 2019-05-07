package spaceattack.game.system.graphics;

import spaceattack.consts.Sizes;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IActorGUI;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;

public class StaticImage implements IGameActor, IActorGUI {

    private IActor actor;

    protected float x;
    protected float y;

    protected ITexture texture;

    StaticImage(final ITexture texture, final float x, final float y) {

        this.texture = texture;
        this.x = x;
        this.y = Sizes.GAME_HEIGHT - (y + texture.getHeight());
    }

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

        batch.draw(texture, x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void act(final float delta) {

        // do nothing
    }

    public void setTexture(final ITexture newTexture) {

        texture = newTexture;
    }

    ITexture getTexture() {

        return texture;
    }

    float getX() {

        return x;
    }

    float getY() {

        return y;
    }
}
