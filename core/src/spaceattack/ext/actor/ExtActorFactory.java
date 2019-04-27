package spaceattack.ext.actor;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IActorFactory;
import spaceattack.game.actors.IGameActor;

public enum ExtActorFactory implements IActorFactory {
    INSTANCE;

    @Override
    public IActor create(IGameActor gameActor) {

        return new GdxActor(gameActor);
    }
}
