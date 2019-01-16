package spaceattack.game.input;

import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.ships.IShip;

public interface IGameplayInput extends IInputProcessor
{
	public void registerShip(IShip ship);

	public void registerFireButton(IFireButton button);
}
