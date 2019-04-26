package spaceattack.ext.vector;

import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public enum ExtVectorFactory implements IVectorFactory {
    INSTANCE;

    @Override
    public IVector create(float x, float y) {

        return new GdxVector(x, y);
    }
}
