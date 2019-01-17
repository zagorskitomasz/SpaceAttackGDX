package spaceattack.game.actors;

import spaceattack.consts.Consts;
import spaceattack.game.batch.IBatch;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.IUtils;

public class TimeLabel implements IGameActor
{
	private IUtils utils;
	private ILabel label;
	private long showed;

	public TimeLabel()
	{
		showed = 0;
		utils = Factories.getUtilsFactory().create();
	}

	@Override
	public void draw(IBatch batch,float alpha)
	{
		if (isVisible())
			drawLabel(batch, alpha);
	}

	private void drawLabel(IBatch batch,float alpha)
	{
		label.draw(batch, alpha);
	}

	public void show()
	{
		showed = utils.millis();
	}

	private boolean isVisible()
	{
		return showed + Consts.Gameplay.LABEL_SHOW_TIME > utils.millis();
	}

	@Override
	public IActor getActor()
	{
		return label;
	}

	public void setLabel(ILabel label)
	{
		this.label = label;
	}

	@Override
	public void setActor(IActor label)
	{
		// do nothing
	}

	@Override
	public void act(float delta)
	{
		// do nothing
	}
}
