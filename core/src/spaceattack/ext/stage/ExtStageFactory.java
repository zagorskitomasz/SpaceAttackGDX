package spaceattack.ext.stage;

import spaceattack.game.stages.IStage;
import spaceattack.game.stages.IStageFactory;

public enum ExtStageFactory implements IStageFactory
{
	INSTANCE;

	@Override
	public IStage create()
	{
		return new GdxStage();
	}
}
