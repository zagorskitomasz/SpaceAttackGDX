package com.zagorskidev.spaceattack.stages;

public enum StageFactory
{
	INSTANCE;

	public IStage getStage(Stages type)
	{
		try
		{
			Class<? extends IStage> stageClass = type.getStageClass();

			IStage stage = createInstance(stageClass);
			stage.setType(type);
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
