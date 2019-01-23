package spaceattack.game.actors;

import spaceattack.game.batch.IBatch;

public interface ILabel extends IActor
{
	public void draw(IBatch batch,float alpha);

	public void setText(CharSequence string);

	public void setAlignment(int alignment);
}