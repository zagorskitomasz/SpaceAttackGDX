package spaceattack.game.utils;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.ILabel;
import spaceattack.game.batch.IBatch;

public class GameplayLabel implements IGameActor
{
	private ILabel label;
	
	public GameplayLabel(ILabel label)
	{
		this.label = label;
	}
	
	@Override
	public IActor getActor() 
	{
		return label;
	}

	@Override
	public void draw(IBatch batch, float alpha) 
	{
		label.draw(batch, 0.8f);
	}

	@Override
	public void setActor(IActor actor) 
	{
		// do nothing
	}

	@Override
	public void act(float delta) 
	{
		// do nothing
	}

	public void setText(String text) 
	{
		label.setText(text);
	}
}
