package spaceattack.game.ai.movers;

import spaceattack.consts.Consts;

public class CorrectableCloseFrontChaser extends CorrectableFrontChaser {

    public CorrectableCloseFrontChaser() {

        super();
    }

    @Override
    public MoverType getType() {

        return MoverType.CORRECTABLE_CLOSE_FRONT_CHASER;
    }

    @Override
    protected float getDistance() {

        return Consts.AI.FRONT_CHASER_DISTANCE * 0.75f;
    }
}
