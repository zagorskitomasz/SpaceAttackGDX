package spaceattack.ext.batch;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import spaceattack.ext.actor.GdxLabel;
import spaceattack.game.actors.ILabel;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.ITexture;

class BatchProxy implements IBatch
{
	private Batch realBatch;
	private ShapeRenderer renderer;

	public BatchProxy(Batch batch)
	{
		realBatch = batch;
		renderer = new ShapeRenderer();
	}

	@Override
	public void draw(ITexture texture,float drawingX,float drawingY)
	{
		realBatch.draw((Texture) texture, drawingX, drawingY);
	}

	@Override
	public void draw(ILabel label,float alpha)
	{
		((GdxLabel) label).draw(realBatch, alpha);
	}

	@Override
	public void setColor(float r,float g,float b,int alpha)
	{
		renderer.setColor(r, g, b, alpha);
	}

	@Override
	public void rect(float x,float y,float width,float height)
	{
		realBatch.end();
		renderer.rect(x, y, width, height);
		realBatch.begin();
	}
}
