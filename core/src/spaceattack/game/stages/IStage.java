package spaceattack.game.stages;

import java.util.List;

import spaceattack.game.actors.IGameActor;

public interface IStage {

    void addActorAtBegining(IGameActor actor);

    void addBackground(IGameActor actor);

    void addActor(IGameActor actor);

    List<IGameActor> getGameActors();

    void removeActor(IGameActor actor);

    void updateViewport(int width, int height, boolean centerCamera);

    void draw();

    void act(float delta);

    void addActorJustBeforeGui(IGameActor newActor);

    void setTextInputListener(IInputListener listener);

    void askPlayer(String title, String question);
}
