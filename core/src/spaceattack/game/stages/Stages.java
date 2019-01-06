package spaceattack.game.stages;

import com.zagorskidev.spaceattack.stages.impl.MainMenuStage;
import com.zagorskidev.spaceattack.stages.impl.Mission1Stage;
import com.zagorskidev.spaceattack.stages.impl.Mission2Stage;
import com.zagorskidev.spaceattack.stages.impl.Mission3Stage;
import com.zagorskidev.spaceattack.stages.impl.MissionsStage;

public enum Stages
{
	//@formatter:off
	MAIN_MENU(MainMenuStage.class, Type.GUI),
	MISSIONS(MissionsStage.class, Type.GUI),
	MISSION_1(Mission1Stage.class, Type.GAMEPLAY),
	MISSION_2(Mission2Stage.class, Type.GAMEPLAY),
	MISSION_3(Mission3Stage.class, Type.GAMEPLAY);
	//@formatter:on

	private static final String MISSION_STAGE_PREFIX = "MISSION_";

	private Class<? extends IGameStage> stageClass;
	private Type type;

	private Stages(Class<? extends IGameStage> stageClass,Type type)
	{
		this.stageClass = stageClass;
		this.type = type;
	}

	public Class<? extends IGameStage> getStageClass()
	{
		return stageClass;
	}

	public Type getStageType()
	{
		return type;
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

	public enum Type
	{
		GUI,GAMEPLAY;
	}
}
