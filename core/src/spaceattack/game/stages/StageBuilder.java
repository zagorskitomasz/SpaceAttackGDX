package spaceattack.game.stages;

import spaceattack.game.StageResult;

public enum StageBuilder
{
	INSTANCE;

	public IGameStage getStage(StageResult result)
	{
		try
		{
			Class<? extends IGameStage> stageClass = result.getNextStage().getStageClass();

			IGameStage stage = createInstance(stageClass);
			stage.setType(result.getNextStage());
			stage.setGameProgress(result.getGameProgress());
			return stage;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	IGameStage createInstance(Class<? extends IGameStage> stageClass) throws InstantiationException,IllegalAccessException
	{
		return stageClass.newInstance();
	}
}
