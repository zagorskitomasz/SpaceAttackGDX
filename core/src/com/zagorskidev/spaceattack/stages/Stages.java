package com.zagorskidev.spaceattack.stages;

import com.zagorskidev.spaceattack.stages.impl.MainMenuStage;
import com.zagorskidev.spaceattack.stages.impl.Mission01Stage;
import com.zagorskidev.spaceattack.stages.impl.MissionsStage;

public enum Stages
{
	//@formatter:off
	MAIN_MENU(MainMenuStage.class),
	MISSIONS(MissionsStage.class),
	MISSION_01(Mission01Stage.class);
	//@formatter:on

	private Class<? extends IStage> stageClass;

	private Stages(Class<? extends IStage> stageClass)
	{
		this.stageClass = stageClass;
	}

	public Class<? extends IStage> getStageClass()
	{
		return stageClass;
	}
}
