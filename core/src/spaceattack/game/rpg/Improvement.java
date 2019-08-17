package spaceattack.game.rpg;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Improvement implements Information {

    RED_LASER_MASTERY("Red laser mastery",
            "Increases red laser damage\nlevel 4 - double shots\nlevel 8 - distracted shots", 1),
    REGENERATION("Regeneration", "You recover HP and energy faster", 1),
    GREEN_LASER_MASTERY("Green laser mastery", "Increases green laser damage", 5),
    SNIPER("Sniper", "Increases all missiles speed", 5),
    AMMO_COLLECTOR("Ammo collector", "Increases special weapon's ammo", 10),
    SPRINTER("Sprinter", "Your ship is very fast,\nbut flying burns energy", 10),
    ADRENALINE("Adrenaline", "Increases damage and speed\nwhen low on HP", 15),
    ABSORBER("Absorber", "Transforms damage taken into energy", 15),
    FEAR("Fear", "Chance that hurt enemies\nwill escape", 20),
    BERSERKER("Berserker",
            "You are slow, but each killed enemy\nstrongly increases your damage\nand speed for short time", 20);

    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 10;
    public static final int POINTS_PER_LEVEL = 1;

    private final String infoName;
    private final String details;
    private final int levelRequired;

    private Improvement(final String infoName, final String details, final int levelRequired) {

        this.infoName = infoName;
        this.details = details;
        this.levelRequired = levelRequired;
    }

    @Override
    public String getName() {

        return infoName;
    }

    @Override
    public String getDetails() {

        return details;
    }

    public int getLevelRequired() {

        return levelRequired;
    }

    public static Stream<Improvement> stream() {

        return Arrays.asList(values()).stream();
    }
}
