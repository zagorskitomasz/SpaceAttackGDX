package spaceattack.game.stages;

import spaceattack.game.stages.builders.IStageBuilder;
import spaceattack.game.stages.builders.MainMenuStageBuilder;
import spaceattack.game.stages.builders.Mission1StageBuilder;
import spaceattack.game.stages.builders.MissionsStageBuilder;

public enum Stages
{
	//@formatter:off
	MAIN_MENU(MainMenuStageBuilder.INSTANCE),
	MISSIONS(MissionsStageBuilder.INSTANCE),
	MISSION_1(Mission1StageBuilder.INSTANCE),
	MISSION_2(Mission1StageBuilder.INSTANCE),
	MISSION_3(Mission1StageBuilder.INSTANCE);
	// TODO missions 2-3
	//@formatter:on

	private static final String MISSION_STAGE_PREFIX = "MISSION_";

	private IStageBuilder stageBuilder;

	private Stages(IStageBuilder stageBuilder)
	{
		this.stageBuilder = stageBuilder;
	}

	public IStageBuilder getStageBuilder()
	{
		return stageBuilder;
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
