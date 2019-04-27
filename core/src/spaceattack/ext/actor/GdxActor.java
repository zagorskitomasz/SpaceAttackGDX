package spaceattack.ext.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import spaceattack.ext.batch.BatchProxyHolder;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;

public class GdxActor extends Actor implements IActor {

    private IGameActor gameActor;

    public GdxActor(IGameActor actor) {

        gameActor = actor;
    }

    @Override
    public void draw(Batch batch, float alpha) {

        gameActor.draw(BatchProxyHolder.INSTANCE.get(), alpha);
    }

    @Override
    public void act(float delta) {

        gameActor.act(delta);
    }
}
