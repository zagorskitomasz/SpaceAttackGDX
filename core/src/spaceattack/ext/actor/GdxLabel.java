package spaceattack.ext.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import spaceattack.consts.Sizes;
import spaceattack.ext.batch.BatchProxyHolder;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.ILabel;
import spaceattack.game.batch.IBatch;

public class GdxLabel extends Label implements ILabel
{
	private IGameActor gameActor;
	private boolean draw;

	public GdxLabel(CharSequence text,LabelStyle style)
	{
		super(text, style);
		setFontScale(Sizes.X_FACTOR,Sizes.Y_FACTOR);
	}

	@Override
	public void setGameActor(IGameActor actor)
	{
		gameActor = actor;
	}

	@Override
	public void draw(IBatch batch, float alpha)
	{
		batch.draw(this,alpha);
	}

	@Override
	public void disableDrawing()
	{
		draw = false;
	}

	@Override
	public void enableDawing()
	{
		draw = true;
	}

	@Override
	public void draw(Batch batch, float alpha)
	{
		if(gameActor == null || draw)
		{
			super.draw(batch, alpha);

			if(draw)
			{
				gameActor.draw(BatchProxyHolder.INSTANCE.get(), alpha);
			}
		}
	}
}
