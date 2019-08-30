package spaceattack.game.input;

import java.util.HashSet;
import java.util.Set;

import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;

public class MissionInputHandler implements IGameplayInput {

    private final Set<IFireButton> buttons;
    private IUtils utils;

    public MissionInputHandler() {

        buttons = new HashSet<>();
    }

    @Override
    public void setUtils(final IUtils utils) {

        this.utils = utils;
    }

    @Override
    public void registerShip(final IShip ship) {

        IPool pool = ship.getEnergyPool();

        for (IFireButton button : buttons) {
            button.setEnergyPool(pool);
        }
    }

    @Override
    public void registerFireButton(final IFireButton button) {

        buttons.add(button);
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {

        for (IFireButton fireButton : buttons) {
            fireButton.touchDown(screenX, screenY);

        }
        return true;
    }

    @Override
    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {

        for (IFireButton fireButton : buttons) {
            if (fireButton.touchUp(screenX, screenY)) {
                return true;
            }
        }
        return true;
    }

    @Override
    public IVector getTouch(final int pointer) {

        return utils.getTouch(pointer);
    }
}
