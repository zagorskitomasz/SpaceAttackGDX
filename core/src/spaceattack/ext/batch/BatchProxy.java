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

class BatchProxy implements IBatch {

    private Batch realBatch;
    private final ShapeRenderer renderer;

    public BatchProxy() {

        renderer = new ShapeRenderer();
    }

    public void set(final Batch batch) {

        realBatch = batch;
    }

    @Override
    public void draw(final ITexture texture, final float drawingX, final float drawingY) {

        realBatch.draw(((IRegionHolder) texture).getTextureRegion(), drawingX, drawingY);
    }

    @Override
    public void draw(final ILabel label, final float alpha) {

        ((GdxLabel) label).draw(realBatch, alpha);
    }

    @Override
    public void rect(final Rect... rects) {

        realBatch.end();
        renderer.setProjectionMatrix(realBatch.getProjectionMatrix());
        renderer.begin(ShapeType.Filled);

        for (Rect rect : rects) {
            setColor(rect.getRed(), rect.getGreen(), rect.getBlue(), 0);
            renderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        }
        renderer.end();
        realBatch.begin();
    }

    @Override
    public void draw(final ITexture texture, final float x, final float y, final float width, final float height) {

        realBatch.draw(((IRegionHolder) texture).getTextureRegion(), x, y, width, height);
    }

    private void setColor(final float r, final float g, final float b, final int alpha) {

        renderer.setColor(r, g, b, alpha);
    }

    @Override
    public void draw(final ITexture texture, final float x, final float y, final float originX, final float originY,
            final float width, final float height,
            final float scaleX, final float scaleY, final float rotation) {

        realBatch.draw(((IRegionHolder) texture).getTextureRegion(), x, y, originX, originY, width, height, scaleX,
                scaleY, rotation);
    }
}
