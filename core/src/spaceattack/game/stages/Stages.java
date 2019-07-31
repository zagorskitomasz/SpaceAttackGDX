package spaceattack.game.stages;

import spaceattack.game.stages.impl.IStageBuilder;
import spaceattack.game.stages.impl.MainMenuStageBuilder;
import spaceattack.game.stages.impl.Mission10StageBuilder;
import spaceattack.game.stages.impl.Mission11StageBuilder;
import spaceattack.game.stages.impl.Mission12StageBuilder;
import spaceattack.game.stages.impl.Mission13StageBuilder;
import spaceattack.game.stages.impl.Mission14StageBuilder;
import spaceattack.game.stages.impl.Mission15StageBuilder;
import spaceattack.game.stages.impl.Mission1StageBuilder;
import spaceattack.game.stages.impl.Mission2StageBuilder;
import spaceattack.game.stages.impl.Mission3StageBuilder;
import spaceattack.game.stages.impl.Mission4StageBuilder;
import spaceattack.game.stages.impl.Mission5StageBuilder;
import spaceattack.game.stages.impl.Mission6StageBuilder;
import spaceattack.game.stages.impl.Mission7StageBuilder;
import spaceattack.game.stages.impl.Mission8StageBuilder;
import spaceattack.game.stages.impl.Mission9StageBuilder;
import spaceattack.game.stages.impl.MissionsStageBuilder;
import spaceattack.game.stages.impl.PlayerSelectionStageBuilder;

public enum Stages {
    // @formatter:off
    PLAYERS_MENU(PlayerSelectionStageBuilder.class),
    MAIN_MENU(MainMenuStageBuilder.class),
    STATS(null),
    SKILLS(null),
    WEAPONS(null),
    MISSIONS(MissionsStageBuilder.class),
    MISSION_1(Mission1StageBuilder.class),
    MISSION_2(Mission2StageBuilder.class),
    MISSION_3(Mission3StageBuilder.class),
    MISSION_4(Mission4StageBuilder.class),
    MISSION_5(Mission5StageBuilder.class),
    MISSION_6(Mission6StageBuilder.class),
    MISSION_7(Mission7StageBuilder.class),
    MISSION_8(Mission8StageBuilder.class),
    MISSION_9(Mission9StageBuilder.class),
    MISSION_10(Mission10StageBuilder.class),
    MISSION_11(Mission11StageBuilder.class),
    MISSION_12(Mission12StageBuilder.class),
    MISSION_13(Mission13StageBuilder.class),
    MISSION_14(Mission14StageBuilder.class),
    MISSION_15(Mission15StageBuilder.class);
    // @formatter:on

    private static final String MISSION_STAGE_PREFIX = "MISSION_";

    private Class<? extends IStageBuilder> stageBuilderClass;

    private Stages(final Class<? extends IStageBuilder> stageBuilderClass) {

        this.stageBuilderClass = stageBuilderClass;
    }

    public IStageBuilder getStageBuilder() {

        try {
            return stageBuilderClass.newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Stages getMissionStage(final int mission) {

        for (Stages stage : values()) {
            if (stage.name().equals(MISSION_STAGE_PREFIX + mission)) {
                return stage;
            }
        }
        return null;
    }
}
