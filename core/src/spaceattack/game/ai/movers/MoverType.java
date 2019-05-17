package spaceattack.game.ai.movers;

import spaceattack.game.ai.MoverAI;

public enum MoverType {
    DIRECT_CHASER(DirectChaser.class, false), //
    LEFT_SIDE_CHASER(LeftSideChaser.class, false), //
    RIGHT_SIDE_CHASER(RightSideChaser.class, false), //
    FRONT_CHASER(FrontChaser.class, true), //
    SIDE_FRONT_CHASER(SideFrontChaser.class, true), //
    SLOW_DOWNER(SlowDowner.class, true), //
    CORNERS_CHASER(CornersChaser.class, true), //
    ALL_CORNERS_CHASER(AllCornersChaser.class, true),
    CORRECTABLE_JUMPER(CorrectableJumper.class, true),
    CORRECTABLE_FRONT_CHASER(CorrectableFrontChaser.class, true),
    CORRECTABLE_CLOSE_FRONT_CHASER(CorrectableCloseFrontChaser.class, true);

    private Class<? extends MoverAI> type;
    private boolean special;

    MoverType(final Class<? extends MoverAI> type, final boolean special) {

        this.type = type;
        this.special = special;
    }

    public MoverAI create() {

        try {
            return type.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isSpecial() {

        return special;
    }
}
