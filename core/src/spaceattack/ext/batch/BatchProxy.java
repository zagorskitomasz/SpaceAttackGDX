package spaceattack.ext.batch;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import spaceattack.ext.actor.GdxLabel;
import spaceattack.ext.texture.IRegionHolder;
import spaceattack.game.actors.ILabel;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.Rect;

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
		realBatch.draw(((IRegionHolder) texture).getTextureRegion(), drawingX, drawingY);
	}

	@Override
	public void draw(ILabel label,float alpha)
	{
		((GdxLabel) label).draw(realBatch, alpha);
	}

	@Override
	public void rect(Rect...rects)
	{
		realBatch.end();
		renderer.setProjectionMatrix(realBatch.getProjectionMatrix());
		renderer.begin(ShapeType.Filled);

		for (Rect rect : rects)
		{
			setColor(rect.getRed(), rect.getGreen(), rect.getBlue(), 0);
			renderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
		renderer.end();
		realBatch.begin();
	}

	private void setColor(float r,float g,float b,int alpha)
	{
		renderer.setColor(r, g, b, alpha);
	}
}
