package spaceattack.game.actors.interfaces;

import spaceattack.game.weapons.missiles.Burner;

public interface Ignitable extends Vulnerable {

    void ignite(float burningDPS, long fireDuration);

    void setBurner(Burner burner);

    float getDrawingX();

    float getDrawingY();

    float getWidth();

    float getHeight();

    boolean isImmortal();
}
