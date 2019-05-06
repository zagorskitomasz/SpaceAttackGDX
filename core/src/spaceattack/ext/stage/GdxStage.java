package spaceattack.ext.stage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import spaceattack.ext.actor.GdxActor;
import spaceattack.ext.batch.BatchProxyHolder;
import spaceattack.game.actors.IActorGUI;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.stages.IStage;

public class GdxStage extends Stage implements IStage {

    private final List<IGameActor> actorProxies;

    public GdxStage() {

        actorProxies = new CopyOnWriteArrayList<>();
    }

    @Override
    public void addActorAtBegining(final IGameActor actor) {

        // background always first
        int index = Math.min(1, actorProxies.size());

        actorProxies.add(index, actor);
        getRoot().addActorAt(index, (GdxActor) actor.getActor());
    }

    @Override
    public void updateViewport(final int width, final int height, final boolean centerCamera) {

        super.getViewport().update(width, height, centerCamera);
    }

    @Override
    public void addActor(final IGameActor actor) {

        actorProxies.add(actor);
        super.addActor((Actor) actor.getActor());
    }

    @Override
    public List<IGameActor> getGameActors() {

        return actorProxies;
    }

    @Override
    public void removeActor(final IGameActor actor) {

        actorProxies.remove(actor);
        getActors().removeValue((GdxActor) actor.getActor(), false);
    }

    @Override
    public void draw() {

        BatchProxyHolder.INSTANCE.set(getBatch());
        super.draw();
    }

    @Override
    public void addBackground(final IGameActor actor) {

        actorProxies.add(0, actor);
        getRoot().addActorAt(0, (GdxActor) actor.getActor());
    }

    @Override
    public void addActorJustBeforeGui(final IGameActor newActor) {

        int index = 0;
        for (IGameActor actor : actorProxies) {
            if (!(actor instanceof IActorGUI)) {
                index = Math.max(0, actorProxies.indexOf(actor));
            }
        }
        actorProxies.add(index, newActor);
        getRoot().addActorAt(index, (GdxActor) newActor.getActor());
    }
}
