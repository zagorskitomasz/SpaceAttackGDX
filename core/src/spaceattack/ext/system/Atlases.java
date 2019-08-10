package spaceattack.ext.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import spaceattack.game.system.graphics.Animations;

public enum Atlases {
    // @formatter:off
    FIGHTER_EX("graphics/animations/fighterEx.atlas", Animations.FIGHTER_EX),
    BOSS_EX("graphics/animations/bossEx.atlas", Animations.BOSS_EX),
    MISSILE_EX("graphics/animations/missileEx.atlas", Animations.MISSILE_EX),
    TANK_EX("graphics/animations/tankEx.atlas", Animations.TANK_EX),
    FIRE("graphics/animations/fire.atlas", Animations.FIRE),
    SHIELD("graphics/animations/shield.atlas", Animations.SHIELD),
    TIME_WAVE("graphics/animations/timeWaveUse.atlas", Animations.TIME_WAVE),
    TIME_FREEZE("graphics/animations/timeWave.atlas", Animations.TIME_FREEZE),
    FLAME_P("graphics/animations/flameP.atlas", Animations.FLAME_P),
    FLAME_E("graphics/animations/flameE.atlas", Animations.FLAME_E);
    // @formatter:on

    private TextureAtlas atlas;
    private Animations animation;

    Atlases(final String path, final Animations animation) {

        atlas = new TextureAtlas(Gdx.files.internal(path));
        this.animation = animation;
    }

    public static TextureAtlas getAtlas(final Animations animation) {

        for (Atlases atlas : values()) {
            if (atlas.animation.equals(animation)) {
                return atlas.atlas;
            }
        }
        throw new IllegalArgumentException("No atlas for animation: " + animation.name());
    }
}
