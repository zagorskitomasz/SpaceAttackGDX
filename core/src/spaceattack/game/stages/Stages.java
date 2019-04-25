package spaceattack.game.stages;

import spaceattack.game.stages.impl.IStageBuilder;
import spaceattack.game.stages.impl.MainMenuStageBuilder;
import spaceattack.game.stages.impl.Mission1StageBuilder;
import spaceattack.game.stages.impl.Mission2StageBuilder;
import spaceattack.game.stages.impl.Mission3StageBuilder;
import spaceattack.game.stages.impl.Mission4StageBuilder;
import spaceattack.game.stages.impl.Mission5StageBuilder;
import spaceattack.game.stages.impl.Mission6StageBuilder;
import spaceattack.game.stages.impl.MissionsStageBuilder;

public enum Stages
{
	//@formatter:off
	MAIN_MENU(MainMenuStageBuilder.class),
	MISSIONS(MissionsStageBuilder.class),
	MISSION_1(Mission1StageBuilder.class),
	MISSION_2(Mission2StageBuilder.class),
	MISSION_3(Mission3StageBuilder.class),
	MISSION_4(Mission4StageBuilder.class),
	MISSION_5(Mission5StageBuilder.class),
	MISSION_6(Mission6StageBuilder.class);
	//@formatter:on

	private static final String MISSION_STAGE_PREFIX = "MISSION_";

	private Class<? extends IStageBuilder> stageBuilderClass;

	private Stages(Class<? extends IStageBuilder> stageBuilderClass)
	{
		this.stageBuilderClass = stageBuilderClass;
	}

	public IStageBuilder getStageBuilder()
	{
		try
		{
			return stageBuilderClass.newInstance();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
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
