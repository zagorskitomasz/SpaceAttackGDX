package spaceattack.game.actors;

import spaceattack.game.batch.IBatch;

public interface ILabel extends IActor {

    void setGameActor(IGameActor actor);

    void draw(IBatch batch, float alpha);

    void setText(CharSequence string);

    void setAlignment(int alignment);

    void disableDrawing();

    void enableDawing();

    void pack();

    void update(String attributeDetails);
}
