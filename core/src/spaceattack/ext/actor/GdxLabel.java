package spaceattack.ext.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import spaceattack.game.actors.ILabel;
import spaceattack.game.batch.IBatch;

public class GdxLabel extends Label implements ILabel
{

	public GdxLabel(CharSequence text,LabelStyle style)
	{
		super(text, style);
	}

	@Override
	public void draw(IBatch batch,float alpha)
	{
		batch.draw(this, alpha);
	}
}
