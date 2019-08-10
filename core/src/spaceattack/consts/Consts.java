package spaceattack.consts;

import spaceattack.game.rpg.Attributes;

public class Consts {

    public static final String DEFAULT = "default";
    public static final String RED_BTN = "red-btn";
    public static final String DIALOG = "Dialog";

    public static class Metagame {

        public static final int MISSIONS_IN_ACT = 3;
        public static final int ACTS_NUMBER = 5;
        public static final int FPS = 20;
        public static final int PLAYER_SLOTS = 6;
    }

    public static class Weapons {

        public static final float LASER_ATTACKS_PER_SECOND = 4;
        public static final float TARGETED_LASER_ATTACKS_PER_SECOND = 3.2f;
        public static final float ROCKET_ATTACKS_PER_SECOND = 1;
        public static final float MINE_ATTACKS_PER_SECOND = 1;
        public static final float SLOW_MINE_ATTACKS_PER_SECOND = 0.5f;
        public static final float SHIELD_ATTACKS_PER_SECOND = 5;
        public static final float TIME_WAVE_ATTACKS_PER_SECOND = 0.33f;

        public static final float RED_LASER_DMG_PER_ATTR = 2;
        public static final float RED_LASER_SPEED_PER_ATTR = 2 * Sizes.Y_FACTOR;
        public static final float RED_LASER_COST_PER_ATTR = 2;

        public static final float TRED_LASER_DMG_PER_ATTR = 2;
        public static final float TRED_LASER_SPEED_PER_ATTR = 2 * Sizes.Y_FACTOR;
        public static final float TRED_LASER_COST_PER_ATTR = 0;

        public static final float DISTRACTED_RED_DMG_PER_ATTR = 2;
        public static final float DISTRACTED_RED_SPEED_PER_ATTR = 2 * Sizes.Y_FACTOR;
        public static final float DISTRACTED_RED_COST_PER_ATTR = 2;

        public static final float MASSIVE_RED_DMG_PER_ATTR = 2;
        public static final float MASSIVE_RED_SPEED_PER_ATTR = 2 * Sizes.Y_FACTOR;
        public static final float MASSIVE_RED_COST_PER_ATTR = 2;

        public static final float DOUBLE_RED_DMG_PER_ATTR = 2;
        public static final float DOUBLE_RED_SPEED_PER_ATTR = 2 * Sizes.Y_FACTOR;
        public static final float DOUBLE_RED_COST_PER_ATTR = 2;

        public static final float GREEN_LASER_DMG_PER_ATTR = 4;
        public static final float GREEN_LASER_SPEED_PER_ATTR = 1.7f * Sizes.Y_FACTOR;
        public static final float GREEN_LASER_COST_PER_ATTR = 3;

        public static final float TRIPLE_GREEN_DMG_PER_ATTR = 4;
        public static final float TRIPLE_GREEN_SPEED_PER_ATTR = 1.7f * Sizes.Y_FACTOR;
        public static final float TRIPLE_GREEN_COST_PER_ATTR = 3;

        public static final float ROCKET_DMG_PER_ATTR = 4;
        public static final float ROCKET_SPEED_PER_ATTR = 0;
        public static final float ROCKET_COST_PER_ATTR = 3;

        public static final float DROCKET_DMG_PER_ATTR = 4;
        public static final float DROCKET_SPEED_PER_ATTR = 0;
        public static final float DROCKET_COST_PER_ATTR = 3;

        public static final float MINER_DMG_PER_ATTR = 4;
        public static final float MINER_SPEED_PER_ATTR = 0;
        public static final float MINER_COST_PER_ATTR = 3;

        public static final float SHIELD_DMG_PER_ATTR = 4;
        public static final float SHIELD_SPEED_PER_ATTR = 0;
        public static final float SHIELD_COST_PER_ATTR = 3;

        public static final float FLAME_DMG_PER_ATTR = 4;
        public static final float FLAME_SPEED_PER_ATTR = 0;
        public static final float FLAME_COST_PER_ATTR = 3;
        public static final long FLAME_DURATION = 5000;

        public static final float WAVE_DMG_PER_ATTR = 4;
        public static final float WAVE_SPEED_PER_ATTR = 0;
        public static final float WAVE_COST_PER_ATTR = 3;

        public static final float LASER_RADIUS = 8;
        public static final float ROCKET_RADIUS = 10;
        public static final float MINE_RADIUS = 20;
        public static final float SHIELD_RADIUS = 130;
        public static final float FLAME_RADIUS = 100;
        public static final long MINE_DELAY = 5000;
        public static final long SLOW_MINE_DELAY = 7000;
        public static final long MINE_OVERHEAT_DELAY = 500;
        public static final float FLYING_MINE_SPEED = 3;
    }

    public static class Gameplay {

        public static final long LABEL_SHOW_TIME = 4500;
        public static final float SHIP_TURN_THRESHOLD = 2.5f;
        public static final int NO_BOSS_TANKS_POOL = 5;
        public static final int MINOR_BOSS_TANKS_POOL = 5;
        public static final int MAJOR_BOSS_TANKS_POOL = 4;
        public static final int MAX_EXP_LEVEL = 99;
    }

    public static class Align {

        static public final int top = 1 << 1;
        static public final int left = 1 << 3;
        static public final int right = 1 << 4;

        static public final int topLeft = top | left;
        static public final int topRight = top | right;
    }

    public static class AI {

        public static final float FIGHTERS_PER_SECOND = 0.1f;
        public static final float CHASERS_PER_SECOND = 0.07f;
        public static final float TANKS_PER_SECOND = 0.04f;

        public static final long FIRST_FIGHTER_AFTER_MILLIS = 3000;
        public static final long FIRST_CHASER_AFTER_MILLIS = 10000;
        public static final long FIRST_TANK_AFTER_MILLIS = 23000;

        public static final double FIGHTER_POWER_UP_CHANCE = 0.6;
        public static final double FIGHTER_HP_UP_CHANCE = 0.3;
        public static final double FIGHTER_ENE_UP_CHANCE = 0.4;

        public static final float POOL_INCREASE_PERCENT = 0.5f;
        public static final float POWER_UP_SPEED = 7 * Sizes.Y_FACTOR;
        public static final float FRONT_CHASER_DISTANCE = 380 * Sizes.Y_FACTOR;

        public static final float JUMPER_CORRECTIONS_PER_SEC = 1;
        public static final double JUMPER_PLAYER_DISTANCE = 280 * Sizes.Y_FACTOR;
        public static final float SIDE_CHASER_MOVEMENT = 220 * Sizes.X_FACTOR;
        public static final float POWER_UP_FINAL_BOSS_FREQ = 0.2f;
        public static final float FINAL_EXPLOSIONS_FREQ = 2;
    }

    public static class Pools {

        public static final float HP_PER_ATTR = 6;
        public static final float HP_REGEN_PER_ATTR = 1f;

        public static final float ENERGY_PER_ATTR = 10;
        public static final float ENERGY_REGEN_PER_ATTR = 2;
    }

    public static class Explosions {

        public static final float FIGHTER_EX_DMG = 20;
        public static final float FIGHTER_EX_FIRE_BASE_DMG = 10;
        public static final float FIGHTER_EX_FIRE_DMG_PER_STRENGTH = 2;
        public static final float FIGHTER_EX_RADIUS = 75;

        public static final float TANK_EX_DMG = 40;
        public static final float TANK_EX_FIRE_BASE_DMG = 20;
        public static final float TANK_EX_FIRE_DMG_PER_STRENGTH = 4;
        public static final float TANK_EX_RADIUS = 125;

        public static final float BOSS_EX_DMG = 0;
        public static final float BOSS_EX_FIRE_BASE_DMG = 0;
        public static final float BOSS_EX_RADIUS = 0;

        public static final float MISSILE_EX_DMG = 2;
        public static final float MISSILE_EX_FIRE_DMG = 0.5f;
        public static final float MISSILE_EX_RADIUS = 100;

        public static final float MINE_EX_DMG = 2;
        public static final float MINE_EX_FIRE_DMG = 0.5f;
        public static final float MINE_EX_RADIUS = 125;

        public static final long FIRE_BASE_DURATION = 4000;
        public static final long FIRE_DURATION_PER_DMG = 5;
    }

    public static class AttributesStarters {

        public static final Starter FIGHTER = new Starter(3, 7, 5, 5, 1, 1.5f, 1, 1);
        public static final Starter CHASER = new Starter(5, 3, 5, 7, 1, 1, 1, 1.5f);
        public static final Starter TANK = new Starter(5, 7, 5, 3, 1, 1.5f, 1, 0.5f);

        public static final Starter MINOR_AI = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
        public static final Starter MINOR_AII = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
        public static final Starter MINOR_AIII = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
        public static final Starter MINOR_AIV = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
        public static final Starter MINOR_AV = new Starter(10, 10, 10, 5, 2, 2, 2, 1);

        public static final Starter MAJOR_AI = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
        public static final Starter MAJOR_AII = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
        public static final Starter MAJOR_AIII = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
        public static final Starter MAJOR_AIV = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
        public static final Starter MAJOR_AV = new Starter(10, 10, 10, 5, 2, 2, 2, 1);

        public static final Starter SPACE_STATION = new Starter(10, 50, 10, 5, 2, 5, 2, 1);
        public static final Starter STATION_HELPER_I = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
        public static final Starter STATION_HELPER_II = new Starter(10, 10, 10, 5, 2, 2, 2, 1);
    }

    public static class Starter {

        private final int baseArmory;
        private final int baseShields;
        private final int baseBattery;
        private final int baseEngine;
        private final float armoryPerLevel;
        private final float shieldsPerLevel;
        private final float batteryPerLevel;
        private final float enginePerLevel;

        public Starter(final int baseArmory, final int baseShields, final int baseBattery, final int baseEngine,
                final float armoryPerLevel, final float shieldsPerLevel, final float batteryPerLevel,
                final float enginePerLevel) {

            this.baseArmory = baseArmory;
            this.baseShields = baseShields;
            this.baseBattery = baseBattery;
            this.baseEngine = baseEngine;
            this.armoryPerLevel = armoryPerLevel;
            this.shieldsPerLevel = shieldsPerLevel;
            this.batteryPerLevel = batteryPerLevel;
            this.enginePerLevel = enginePerLevel;
        }

        public Attributes get(final int level) {

            return new Attributes(
                    baseArmory + Math.round(armoryPerLevel * level),
                    baseShields + Math.round(shieldsPerLevel * level),
                    baseBattery + Math.round(batteryPerLevel * level),
                    baseEngine + Math.round(enginePerLevel * level));
        }
    }
}
