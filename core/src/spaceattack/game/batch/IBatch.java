package spaceattack.game.batch;

import spaceattack.game.actors.ILabel;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.Rect;

public interface IBatch
{
	public void draw(ITexture texture,float drawingX,float drawingY);

	public void draw(ILabel label,float alpha);

	public void rect(Rect...rects);

    public void draw(ITexture texture, float x, float y, float width, float height);
}
