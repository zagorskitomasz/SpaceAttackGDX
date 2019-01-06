package spaceattack.ext.batch;

import com.badlogic.gdx.graphics.g2d.Batch;

import spaceattack.game.batch.IBatch;

public enum BatchProxyHolder
{
	INSTANCE;

	private IBatch batchProxy;

	public IBatch get()
	{
		return batchProxy;
	}

	public void set(Batch batch)
	{
		batchProxy = new BatchProxy(batch);
	}
}
