package spaceattack.ext.batch;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.ITexture;

class BatchProxy implements IBatch
{
	private Batch realBatch;

	public BatchProxy(Batch batch)
	{
		realBatch = batch;
	}

	@Override
	public void draw(ITexture texture,float drawingX,float drawingY)
	{
		realBatch.draw((Texture) texture, drawingX, drawingY);
	}
}
