package spaceattack.game.rpg;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Attribute {

    ARMORY,
    SHIELDS,
    BATTERY,
    ENGINE;

    public static final int MIN_VALUE = 8;

    public static Stream<Attribute> stream() {

        return Arrays.asList(values()).stream();
    }
}
