package spaceattack.game.batch;

import spaceattack.game.actors.ILabel;
import spaceattack.game.system.graphics.ITexture;

public interface IBatch
{
	public void draw(ITexture texture,float drawingX,float drawingY);

	public void draw(ILabel label,float alpha);

	public void setColor(float r,float g,float b,int alpha);

	public void rect(float x,float y,float width,float height);
}
