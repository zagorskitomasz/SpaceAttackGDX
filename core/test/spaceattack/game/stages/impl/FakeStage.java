package spaceattack.game.stages.impl;

import java.util.LinkedList;
import java.util.List;

import spaceattack.game.actors.IGameActor;
import spaceattack.game.stages.IInputListener;
import spaceattack.game.stages.IStage;

public class FakeStage implements IStage {

    private final List<IGameActor> actors;

    public FakeStage() {

        actors = new LinkedList<>();
    }

    @Override
    public void addActorAtBegining(final IGameActor actor) {

        actors.add(Math.min(1, actors.size()), actor);
    }

    @Override
    public void addActor(final IGameActor actor) {

        actors.add(actor);
    }

    @Override
    public List<IGameActor> getGameActors() {

        return actors;
    }

    @Override
    public void removeActor(final IGameActor actor) {

        actors.remove(actor);
    }

    @Override
    public void updateViewport(final int width, final int height, final boolean centerCamera) {

        // do nothing
    }

    @Override
    public void draw() {

        // do nothing
    }

    @Override
    public void act(final float delta) {

        actors.forEach(actor -> actor.act(delta));
    }

    @Override
    public void addBackground(final IGameActor actor) {

        actors.add(0, actor);
    }

    @Override
    public void addActorJustBeforeGui(final IGameActor actor) {

        actors.add(Math.min(1, actors.size()), actor);
    }

    @Override
    public void setTextInputListener(final IInputListener listener) {

        // do nothing
    }

    @Override
    public void askPlayer(final String title, final String question) {

        // do nothing
    }
}
