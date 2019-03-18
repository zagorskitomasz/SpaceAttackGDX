package spaceattack.game.ai.movers;

import spaceattack.game.ai.MoverAI;

public enum MoverType
{
	DIRECT_CHASER(DirectChaser.class), //
	LEFT_SIDE_CHASER(LeftSideChaser.class), //
	RIGHT_SIDE_CHASER(RightSideChaser.class), // 
	FRONT_CHASER(FrontChaser.class), //
	SLOW_DOWNER(SlowDowner.class), //
	CORNERS_CHASER(CornersChaser.class);

	private Class<? extends MoverAI> type;

	MoverType(Class<? extends MoverAI> type)
	{
		this.type = type;
	}

	public MoverAI create()
	{
		try
		{
			return type.newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
