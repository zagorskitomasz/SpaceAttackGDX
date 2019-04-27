package spaceattack.game.actors.interfaces;

import spaceattack.game.weapons.MissilesLauncher;

public interface Explosive {

    public void setExplosion(Launchable explosion);

    public void setMissilesLauncher(MissilesLauncher launcher);

    public void explode();
}
