package spaceattack.game.buttons;

import spaceattack.game.stages.Stages;
import spaceattack.game.stages.impl.MissionsStage;

public class LaunchMissionButtonListener extends ChangeStageButtonListener {

    private int gridPosition;

    public LaunchMissionButtonListener(MissionsStage stage, int gridPosition) {

        super(stage, Stages.MISSION_1);
        this.gridPosition = gridPosition;
    }

    @Override
    public void clicked() {

        nextStage = Stages.getMissionStage(((MissionsStage) stage).calculateMission(gridPosition));
        super.clicked();
    }
}
