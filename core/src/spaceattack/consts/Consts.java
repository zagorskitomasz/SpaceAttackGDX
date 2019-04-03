package spaceattack.consts;

public class Consts
{
	public static final String DEFAULT = "default";
	public static final String RED_BTN = "red-btn";
	public static final String DIALOG = "Dialog";

	public static class Metagame
	{
		public static final int MISSIONS_IN_ACT = 3;
		public static final int ACTS_NUMBER = 3;
		public static final int FPS = 20;
	}

	public static class Weapons
	{
		public static final int LASER_ATTACKS_PER_SECOND = 4;
		public static final int TARGETED_LASER_ATTACKS_PER_SECOND = 2;
		public static final int ROCKET_ATTACKS_PER_SECOND = 1;

		public static final float RED_LASER_BASE_DMG = 10;
		public static final float RED_LASER_DMG_PER_LEVEL = 3;
		public static final float RED_LASER_BASE_SPEED = 15 * Sizes.Y_FACTOR;
		public static final float RED_LASER_SPEED_PER_LEVEL = 2 * Sizes.Y_FACTOR;
		public static final float RED_LASER_BASE_COST = 10;
		public static final float RED_LASER_COST_PER_LEVEL = 2;

		public static final float GREEN_LASER_BASE_DMG = 20;
		public static final float GREEN_LASER_DMG_PER_LEVEL = 5;
		public static final float GREEN_LASER_BASE_SPEED = 10 * Sizes.Y_FACTOR;
		public static final float GREEN_LASER_SPEED_PER_LEVEL = 1.5f * Sizes.Y_FACTOR;
		public static final float GREEN_LASER_BASE_COST = 20;
		public static final float GREEN_LASER_COST_PER_LEVEL = 3;

		public static final int ROCKET_BASE_COST = 20;
		public static final int ROCKET_COST_PER_LEVEL = 3;

		public static final float LASER_RADIUS = 8;
		public static final float ROCKET_RADIUS = 10;
	}

	public static class Gameplay
	{
		public static final long LABEL_SHOW_TIME = 4500;
		public static final float SHIP_TURN_THRESHOLD = 2.5f;
	}

	public static class Align
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

	public static class AI
	{
		public static final float FIGHTERS_PER_SECOND = 0.1f;
		public static final float CHASERS_PER_SECOND = 0.06f;
		public static final float TANKS_PER_SECOND = 0.03f;
		
		public static final long FIRST_FIGHTER_AFTER_MILLIS = 3000;
		public static final long FIRST_CHASER_AFTER_MILLIS = 10000;
		public static final long FIRST_TANK_AFTER_MILLIS = 23000;

		public static final double FIGHTER_POWER_UP_CHANCE = 0.6;
		public static final double FIGHTER_HP_UP_CHANCE = 0.3;
		public static final double FIGHTER_ENE_UP_CHANCE = 0.4;

		public static final float POOL_INCREASE_PERCENT = 0.5f;
		public static final float POWER_UP_SPEED = 7 * Sizes.Y_FACTOR;
		public static final float FRONT_CHASER_DISTANCE = 380 * Sizes.Y_FACTOR;
	}
	
	public static class POOLS
	{
		public static final int PLAYER_HP_BASE_AMOUNT = 60;
		public static final int PLAYER_HP_INCREASE_PER_LEVEL = 15;
		public static final int PLAYER_HP_BASE_REGEN = 10;
		public static final int PLAYER_HP_REGEN_PER_LEVEL = 2;

		public static final int PLAYER_ENERGY_BASE_AMOUNT = 80;
		public static final int PLAYER_ENERGY_INCREASE_PER_LEVEL = 20;
		public static final int PLAYER_ENERGY_BASE_REGEN = 20;
		public static final int PLAYER_ENERGY_REGEN_PER_LEVEL = 4;

		public static final int FIGHTER_HP_BASE_AMOUNT = 50;
		public static final int FIGHTER_HP_INCREASE_PER_LEVEL = 10;
		public static final int FIGHTER_HP_BASE_REGEN = 5;
		public static final int FIGHTER_HP_REGEN_PER_LEVEL = 1;

		public static final int FIGHTER_ENERGY_BASE_AMOUNT = 20;
		public static final int FIGHTER_ENERGY_INCREASE_PER_LEVEL = 10;
		public static final int FIGHTER_ENERGY_BASE_REGEN = 10;
		public static final int FIGHTER_ENERGY_REGEN_PER_LEVEL = 2;

		public static final int CHASER_HP_BASE_AMOUNT = 30;
		public static final int CHASER_HP_INCREASE_PER_LEVEL = 8;
		public static final int CHASER_HP_BASE_REGEN = 4;
		public static final int CHASER_HP_REGEN_PER_LEVEL = 1;

		public static final int CHASER_ENERGY_BASE_AMOUNT = 30;
		public static final int CHASER_ENERGY_INCREASE_PER_LEVEL = 15;
		public static final int CHASER_ENERGY_BASE_REGEN = 10;
		public static final int CHASER_ENERGY_REGEN_PER_LEVEL = 2;

		public static final int TANK_HP_BASE_AMOUNT = 80;
		public static final int TANK_HP_INCREASE_PER_LEVEL = 25;
		public static final int TANK_HP_BASE_REGEN = 5;
		public static final int TANK_HP_REGEN_PER_LEVEL = 1;

		public static final int TANK_ENERGY_BASE_AMOUNT = 30;
		public static final int TANK_ENERGY_INCREASE_PER_LEVEL = 15;
		public static final int TANK_ENERGY_BASE_REGEN = 15;
		public static final int TANK_ENERGY_REGEN_PER_LEVEL = 3;

		public static final int MINOR_BOSS_HP_BASE_AMOUNT = 200;
		public static final int MINOR_BOSS_HP_INCREASE_PER_LEVEL = 50;
		public static final int MINOR_BOSS_HP_BASE_REGEN = 0;
		public static final int MINOR_BOSS_HP_REGEN_PER_LEVEL = 0;

		public static final int MINOR_BOSS_ENERGY_BASE_AMOUNT = 50;
		public static final int MINOR_BOSS_ENERGY_INCREASE_PER_LEVEL = 10;
		public static final int MINOR_BOSS_ENERGY_BASE_REGEN = 20;
		public static final int MINOR_BOSS_ENERGY_REGEN_PER_LEVEL = 5;

		public static final int MAJOR_BOSS_HP_BASE_AMOUNT = 400;
		public static final int MAJOR_BOSS_HP_INCREASE_PER_LEVEL = 100;
		public static final int MAJOR_BOSS_HP_BASE_REGEN = 0;
		public static final int MAJOR_BOSS_HP_REGEN_PER_LEVEL = 0;

		public static final int MAJOR_BOSS_ENERGY_BASE_AMOUNT = 100;
		public static final int MAJOR_BOSS_ENERGY_INCREASE_PER_LEVEL = 20;
		public static final int MAJOR_BOSS_ENERGY_BASE_REGEN = 40;
		public static final int MAJOR_BOSS_ENERGY_REGEN_PER_LEVEL = 10;
		
	}
}
