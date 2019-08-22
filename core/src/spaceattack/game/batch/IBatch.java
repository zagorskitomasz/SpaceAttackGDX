package spaceattack.game.batch;

import spaceattack.game.actors.ILabel;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.Rect;

public interface IBatch {

    void draw(ITexture texture, float drawingX, float drawingY);

    void draw(ILabel label, float alpha);

    void rect(Rect... rects);

    void draw(ITexture texture, float x, float y, float width, float height);

    void draw(ITexture texture, float drawingX, float drawingY, float d, float e, float width, float height,
            float scale, float scale2, float angle);
}
