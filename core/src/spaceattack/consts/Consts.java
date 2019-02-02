package spaceattack.consts;

public class Consts
{
	public static final String DEFAULT = "default";
	public static final String RED_BTN = "red-btn";
	public static final String DIALOG = "Dialog";

	public class Metagame
	{
		public static final int MISSIONS_IN_ACT = 3;
		public static final int ACTS_NUMBER = 3;
		public static final int FPS = 20;
	}

	public class Weapons
	{
		public static final int LASER_ATTACKS_PER_SECOND = 4;

		public static final float RED_LASER_BASE_DMG = 10;
		public static final float RED_LASER_DMG_PER_LEVEL = 3;
		public static final float RED_LASER_BASE_SPEED = 15;
		public static final float RED_LASER_SPEED_PER_LEVEL = 2;
		public static final float RED_LASER_BASE_COST = 10;
		public static final float RED_LASER_COST_PER_LEVEL = 2;

		public static final float GREEN_LASER_BASE_DMG = 20;
		public static final float GREEN_LASER_DMG_PER_LEVEL = 5;
		public static final float GREEN_LASER_BASE_SPEED = 10;
		public static final float GREEN_LASER_SPEED_PER_LEVEL = 1.5f;
		public static final float GREEN_LASER_BASE_COST = 20;
		public static final float GREEN_LASER_COST_PER_LEVEL = 3;

		public static final float LASER_RADIUS = 10;
	}

	public class Gameplay
	{
		public static final long LABEL_SHOW_TIME = 4500;
	}

	public class Align
	{
		static public final int center = 1 << 0;
		static public final int top = 1 << 1;
		static public final int bottom = 1 << 2;
		static public final int left = 1 << 3;
		static public final int right = 1 << 4;

		static public final int topLeft = top | left;
		static public final int topRight = top | right;
		static public final int bottomLeft = bottom | left;
		static public final int bottomRight = bottom | right;
	}

	public class AI
	{
		public static final float FIGHTERS_PER_SECOND = 0.15f;
	}
}
