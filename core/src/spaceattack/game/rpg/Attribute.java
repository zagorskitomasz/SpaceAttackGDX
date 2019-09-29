package spaceattack.game.rpg;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Attribute implements Information {

    ARMORY("Armory", "Your shots are deadlier"),
    SHIELDS("Shields", "You are harder to kill"),
    BATTERY("Battery", "You can shoot more often"),
    ENGINE("Engine", "You are faster");

    public static final int MIN_VALUE = 15;
    public static final int POINTS_PER_LEVEL = 5;

    private final String infoName;
    private final String details;

    private Attribute(final String infoName, final String details) {

        this.infoName = infoName;
        this.details = details;
    }

    @Override
    public String getName() {

        return infoName;
    }

    @Override
    public String getDetails() {

        return details;
    }

    public static Stream<Attribute> stream() {

        return Arrays.asList(values()).stream();
    }
}
