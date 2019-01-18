package spaceattack.game.stages;

import spaceattack.game.input.IInputProcessor;

public abstract class UIStage extends AbstractStage
{
	@Override
	public IInputProcessor getInputProcessor()
	{
		return null;
	}
}
