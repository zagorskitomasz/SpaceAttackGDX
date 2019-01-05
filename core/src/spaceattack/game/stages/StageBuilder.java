package spaceattack.game.stages;

import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageResult;

public enum StageBuilder
{
	INSTANCE;

	public IStage getStage(StageResult result)
	{
		try
		{
			Class<? extends IStage> stageClass = result.getNextStage().getStageClass();

			IStage stage = createInstance(stageClass);
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

	IStage createInstance(Class<? extends IStage> stageClass) throws InstantiationException,IllegalAccessException
	{
		return stageClass.newInstance();
	}
}
