package spaceattack.game.system.graphics;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.ResourceNotLoadedException;

public enum Textures {
    // @formatter:off

    // logos
    LOGO("graphics/logo.png"),
    ACT_1_LOGO("graphics/actLogo1.png"),
    ACT_2_LOGO("graphics/actLogo2.png"),
    ACT_3_LOGO("graphics/actLogo3.png"),
    ACT_4_LOGO("graphics/actLogo4.png"),
    ACT_5_LOGO("graphics/actLogo5.png"),

    // backgrounds
    INTRO_BG("graphics/introBg.jpg"),
    MENU_BACKGROUND("graphics/menuBackground.jpg"),
    M1_BACKGROUND("graphics/m1Background.jpg"),
    M2_BACKGROUND("graphics/m2Background.jpg"),
    M3_BACKGROUND("graphics/m3Background.jpg"),
    M4_BACKGROUND("graphics/m4Background.jpg"),
    M5_BACKGROUND("graphics/m5Background.jpg"),
    M6_BACKGROUND("graphics/m6Background.jpg"),
    M7_BACKGROUND("graphics/m7Background.jpg"),
    M8_BACKGROUND("graphics/m8Background.jpg"),
    M9_BACKGROUND("graphics/m9Background.jpg"),
    M10_BACKGROUND("graphics/m10Background.jpg"),
    M11_BACKGROUND("graphics/m11Background.jpg"),
    M12_BACKGROUND("graphics/m12Background.jpg"),
    M13_BACKGROUND("graphics/m13Background.jpg"),
    M14_BACKGROUND("graphics/m14Background.jpg"),
    M15_BACKGROUND("graphics/m15Background.jpg"),

    // UI
    RED_BUTTON_UP("graphics/gui/redActive.png"),
    RED_BUTTON_DOWN("graphics/gui/redPressed.png"),
    GREEN_BUTTON_UP("graphics/gui/greenActive.png"),
    GREEN_BUTTON_DOWN("graphics/gui/greenPressed.png"),
    YELLOW_BUTTON_UP("graphics/gui/yellowActive.png"),
    YELLOW_BUTTON_DOWN("graphics/gui/yellowPressed.png"),
    FIRE_BUTTON_DISABLED("graphics/gui/disabled.png"),
    COCKPIT_PANEL("graphics/gui/cockpitPanel.png"),
    JOYSTICK_BG("graphics/gui/joystickBg.png"),
    JOYSTICK_HEAD("graphics/gui/joystickHead.png"),

    HP_ENE_BAR("graphics/gui/barHp.png"),
    EXP_BAR("graphics/gui/barExp.png"),

    // ships
    PLAYER_SHIP1_F("graphics/ships/player/form1.png"),
    PLAYER_SHIP1_R("graphics/ships/player/form1R.png"),
    PLAYER_SHIP1_L("graphics/ships/player/form1L.png"),

    PLAYER_SHIP2_F("graphics/ships/player/form2.png"),
    PLAYER_SHIP2_R("graphics/ships/player/form2R.png"),
    PLAYER_SHIP2_L("graphics/ships/player/form2L.png"),

    PLAYER_SHIP3_F("graphics/ships/player/form3.png"),
    PLAYER_SHIP3_R("graphics/ships/player/form3R.png"),
    PLAYER_SHIP3_L("graphics/ships/player/form3L.png"),

    PLAYER_SHIP4_F("graphics/ships/player/form4.png"),
    PLAYER_SHIP4_R("graphics/ships/player/form4R.png"),
    PLAYER_SHIP4_L("graphics/ships/player/form4L.png"),

    PLAYER_SHIP5_F("graphics/ships/player/form5.png"),
    PLAYER_SHIP5_R("graphics/ships/player/form5R.png"),
    PLAYER_SHIP5_L("graphics/ships/player/form5L.png"),

    FIGHTER1("graphics/ships/fighter/form1.png"),
    FIGHTER2("graphics/ships/fighter/form2.png"),
    FIGHTER3("graphics/ships/fighter/form3.png"),
    FIGHTER4("graphics/ships/fighter/form4.png"),
    FIGHTER5("graphics/ships/fighter/form5.png"),

    CHASER1("graphics/ships/chaser/form1.png"),
    CHASER2("graphics/ships/chaser/form2.png"),
    CHASER3("graphics/ships/chaser/form3.png"),
    CHASER4("graphics/ships/chaser/form4.png"),
    CHASER5("graphics/ships/chaser/form5.png"),

    TANK1("graphics/ships/tank/form1.png"),
    TANK2("graphics/ships/tank/form2.png"),
    TANK3("graphics/ships/tank/form3.png"),
    TANK4("graphics/ships/tank/form4.png"),
    TANK5("graphics/ships/tank/form5.png"),

    BOSS1("graphics/ships/boss/form1.png"),
    BOSS2("graphics/ships/boss/form2.png"),
    BOSS3("graphics/ships/boss/form3.png"),
    BOSS4("graphics/ships/boss/form4.png"),
    BOSS5("graphics/ships/boss/form5.png"),

    SPACE_STATION("graphics/ships/boss/station.png"),
    BOSS_HELPER_1("graphics/ships/boss/child1.png"),
    BOSS_HELPER_2("graphics/ships/boss/child2.png"),

    ADRENALINE("graphics/ships/effects/adrenaline.png"),
    FEAR("graphics/ships/effects/fear.png"),
    ZERK1("graphics/ships/effects/zerk1.png"),
    ZERK2("graphics/ships/effects/zerk2.png"),
    ZERK3("graphics/ships/effects/zerk3.png"),

    EARTH_BF("graphics/ships/earth/earthBf.png"),
    EARTH_AF("graphics/ships/earth/earthAf.png"),

    // weapons
    RED_LASER_NS("graphics/weapons/laserNS.png"),
    RED_LASER_WE("graphics/weapons/laserWE.png"),
    RED_LASER_S1("graphics/weapons/laserS1.png"),
    RED_LASER_S2("graphics/weapons/laserS2.png"),
    TURBO_LASER("graphics/weapons/turbo.png"),
    ROCKET_MISSILE_P("graphics/weapons/missileP.png"),
    ROCKET_MISSILE_E("graphics/weapons/missileE.png"),
    MINE("graphics/weapons/mine.png"),

    // power ups
    HP_POWER_UP("graphics/powerups/hpPU.png"),
    ENERGY_POWER_UP("graphics/powerups/energyPU.png"),
    ROCKET_POWER_UP("graphics/powerups/rocketPU.png"),
    MINE_POWER_UP("graphics/powerups/minePU.png"),
    SHIELD_POWER_UP("graphics/powerups/shieldPU.png"),
    WAVE_POWER_UP("graphics/powerups/wavePU.png"),
    FLAME_POWER_UP("graphics/powerups/flamePU.png"),
    ROCKET_SELECTED("graphics/powerups/rocketSelected.png"),
    MINE_SELECTED("graphics/powerups/mineSelected.png"),
    SHIELD_SELECTED("graphics/powerups/shieldSelected.png"),
    WAVE_SELECTED("graphics/powerups/waveSelected.png"),
    FLAME_SELECTED("graphics/powerups/flameSelected.png"),
    ROCKET_ICON("graphics/powerups/rocketIcon.png"),
    MINE_ICON("graphics/powerups/mineIcon.png"),
    SHIELD_ICON("graphics/powerups/shieldIcon.png"),
    WAVE_ICON("graphics/powerups/waveIcon.png"),
    FLAME_ICON("graphics/powerups/flameIcon.png"),
    LOCKED_WEAPON("graphics/powerups/lockedPU.png");
    // @formatter:on

    private String path;
    private ITexture texture;

    private static boolean isTest;

    Textures(final String path) {

        this.path = path;
    }

    public static void load() {

        for (Textures texture : values()) {
            texture.texture = Factories.getTextureFactory().create(texture.path);
        }
    }

    /**
     * Only for JUnit test purposes! This fake method won't load any real textures.
     * It will only suppress TextureNotLoadedException. After fake load
     * Textures#getTexture() method will return null!
     */
    public static void loadForTest() {

        isTest = true;
    }

    public ITexture getTexture() {

        if (texture == null && !isTest) {
            throw new ResourceNotLoadedException(name());
        }

        return texture;
    }
}
