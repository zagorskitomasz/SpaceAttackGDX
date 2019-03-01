package spaceattack.ext.button.image;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.buttons.IProgressButton;
import spaceattack.game.utils.vector.IVector;

public class GdxProgressButton extends Button implements IProgressButton
{
	private IGameActor gameActor;
	private Texture slider;
	private float maxSlide;
	private float slidePercent;
	
	public GdxProgressButton(Drawable background,Texture slider)
	{
		super(background);
		this.slider = slider;
		
		maxSlide = getHeight() - slider.getHeight();
		slidePercent = 50;
	}

	@Override
	public void setGameActor(IGameActor gameActor)
	{
		this.gameActor = gameActor;
	}
	
	@Override 
	public void setPercent(float percent)
	{
		slidePercent = percent;
	}
	
	@Override
	public void draw(Batch batch,float alpha)
	{
		super.draw(batch, alpha);
		batch.draw(slider, getX(), getY() + slidePercent * maxSlide / 100);
	}

	@Override
	public IVector screenToStageCoordinates(IVector touch)
	{
		Vector2 vector = new Vector2(touch.getX(), touch.getY());
		Vector2 result = getStage().screenToStageCoordinates(vector);

		return ExtVectorFactory.INSTANCE.create(result.x, result.y);
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
		gameActor.act(delta);
	}
}
