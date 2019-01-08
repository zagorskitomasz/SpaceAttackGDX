package spaceattack.game.stages;

import spaceattack.game.StageResult;
import spaceattack.game.stages.builders.IStageBuilder;

public enum GameStageFactory
{
	INSTANCE;

	public IGameStage getStage(StageResult result)
	{
		IStageBuilder stageBuilder = result.getNextStage().getStageBuilder();
		IGameStage stage = stageBuilder.build(result.getGameProgress());

		return stage;
	}
}