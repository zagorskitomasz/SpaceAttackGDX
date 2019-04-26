package spaceattack.ext.batch;

import com.badlogic.gdx.graphics.g2d.Batch;

import spaceattack.game.batch.IBatch;

public enum BatchProxyHolder {
    INSTANCE;

    private BatchProxy batchProxy;

    BatchProxyHolder() {

        batchProxy = new BatchProxy();
    }

    public IBatch get() {

        return batchProxy;
    }

    public void set(Batch batch) {

        batchProxy.set(batch);
    }
}
