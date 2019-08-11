package spaceattack.game.weapons;

import java.util.Arrays;

import spaceattack.game.powerup.BuildPowerUpFunction;
import spaceattack.game.powerup.PowerUpBuilder;
import spaceattack.game.system.graphics.Textures;

public enum SpecialWeapons {

    ROCKETS("Rockets", "Launch explosive\nrocket missiles", 0, Textures.ROCKET_POWER_UP, Textures.ROCKET_SELECTED,
            PowerUpBuilder.INSTANCE::rocketMissileHolder),
    MINES("Mines", "Build minefield\nin front of your enemies", 3, Textures.MINE_POWER_UP, Textures.MINE_SELECTED,
            PowerUpBuilder.INSTANCE::mineHolder),
    SHIELD("Energy shield", "Cover your ship\nwith lightnings which hurts\nnearby enemies", 6, Textures.SHIELD_POWER_UP,
            Textures.SHIELD_SELECTED, PowerUpBuilder.INSTANCE::shieldHolder),
    WAVE("Black hole", "Invoke small black holes\nwhich freeze all enemies\nand their missiles", 9,
            Textures.WAVE_POWER_UP, Textures.WAVE_SELECTED, PowerUpBuilder.INSTANCE::waveHolder),
    FLAME("Flamethrower", "Burn your enemies\nand destroy their\nrockets and mines", 12, Textures.FLAME_POWER_UP,
            Textures.FLAME_SELECTED, PowerUpBuilder.INSTANCE::flameHolder);

    public final String weaponName;
    public final String description;
    public final int unlockedOnMission;
    public final Textures uncheckedTexture;
    public final Textures checkedTexture;
    public final BuildPowerUpFunction builder;

    private SpecialWeapons(final String weaponName, final String description, final int unlockedOnMission,
            final Textures uncheckedTexture, final Textures checkedTexture, final BuildPowerUpFunction builder) {

        this.weaponName = weaponName;
        this.description = description;
        this.unlockedOnMission = unlockedOnMission;
        this.checkedTexture = checkedTexture;
        this.uncheckedTexture = uncheckedTexture;
        this.builder = builder;
    }

    public static SpecialWeapons getByName(final String name) {

        return Arrays.asList(values())
                .stream()
                .filter(element -> element.name().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
