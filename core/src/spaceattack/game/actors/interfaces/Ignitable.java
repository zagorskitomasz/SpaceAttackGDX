package spaceattack.game.actors.interfaces;

import spaceattack.game.weapons.missiles.Burner;

public interface Ignitable extends Vulnerable {

    public void ignite(float burningDPS, long fireDuration);

    public void setBurner(Burner burner);

    public float getDrawingX();

    public float getDrawingY();
}
