package spaceattack.ext.gdx.stage;

import spaceattack.game.stages.ExtStage;

public enum GdxStageFactory
{
	INSTANCE;

	public ExtStage create()
	{
		return new GdxStage();
	}
}
