package spaceattack.game.input;

import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;

public interface IGameplayInput extends IInputProcessor
{
	public void registerShip(IShip ship);

	public void registerFireButton(IFireButton button);

	public IVector getTouch();

	public void setUtils(IUtils utils);

    public float getAccelerometrX();
}
