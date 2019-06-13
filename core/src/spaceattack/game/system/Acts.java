package spaceattack.game.system;

import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.NumbersUtils;

public enum Acts {
    I(Textures.ACT_1_LOGO, 1, 5), //
    II(Textures.ACT_2_LOGO, 2, 5), //
    III(Textures.ACT_3_LOGO, 3, 5), //
    IV(Textures.ACT_4_LOGO, 4, 8), //
    V(Textures.ACT_5_LOGO, 5, 8);

    private Textures logo;
    private int powerUpAmmo;
    private int number;

    Acts(final Textures texture, final int number, final int powerUpAmmo) {

        this.logo = texture;
        this.number = number;
        this.powerUpAmmo = powerUpAmmo;
    }

    public Textures getLogo() {

        return logo;
    }

    public int getPowerUpAmmo() {

        return powerUpAmmo;
    }

    public static Acts get(final int actNumber) {

        for (Acts act : values()) {
            if (act.name().equals(NumbersUtils.toRoman(actNumber))) {
                return act;
            }
        }
        return null;
    }

    public int getNumber() {

        return number;
    }
}
