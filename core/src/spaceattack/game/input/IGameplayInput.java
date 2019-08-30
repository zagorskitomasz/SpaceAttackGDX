package spaceattack.game.input;

import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;

public interface IGameplayInput extends IInputProcessor {

    void registerShip(IShip ship);

    void registerFireButton(IFireButton button);

    IVector getTouch(int pointer);

    void setUtils(IUtils utils);
}
