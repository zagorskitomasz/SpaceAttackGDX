package spaceattack.game.powerup;

import spaceattack.game.actors.interfaces.Killable;
import spaceattack.game.actors.interfaces.Launchable;

public interface IPowerUp extends Launchable, Killable {

    public void consumed();

    public void setX(float x);

    public void setY(float y);
}
