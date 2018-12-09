package com.zagorskidev.spaceattack.stages;

import com.zagorskidev.spaceattack.stages.impl.MainMenuStage;
import com.zagorskidev.spaceattack.stages.impl.Mission1Stage;
import com.zagorskidev.spaceattack.stages.impl.Mission2Stage;
import com.zagorskidev.spaceattack.stages.impl.Mission3Stage;
import com.zagorskidev.spaceattack.stages.impl.MissionsStage;

public enum Stages
{
	//@formatter:off
	MAIN_MENU(MainMenuStage.class),
	MISSIONS(MissionsStage.class),
	MISSION_1(Mission1Stage.class),
	MISSION_2(Mission2Stage.class),
	MISSION_3(Mission3Stage.class);
	//@formatter:on

	private static final String MISSION_STAGE_PREFIX = "MISSION_";

	private Class<? extends IStage> stageClass;

	private Stages(Class<? extends IStage> stageClass)
	{
		this.stageClass = stageClass;
	}

	public Class<? extends IStage> getStageClass()
	{
		return stageClass;
	}

	public static Stages getMissionStage(int mission)
	{
		for (Stages stage : values())
		{
			if (stage.name().equals(MISSION_STAGE_PREFIX + mission))
				return stage;
		}
		return null;
	}
}
