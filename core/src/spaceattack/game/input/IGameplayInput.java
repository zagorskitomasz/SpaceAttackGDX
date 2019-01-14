package spaceattack.game.input;

import com.zagorskidev.spaceattack.ships.IShip;

import spaceattack.game.buttons.weapon.IFireButton;

public interface IGameplayInput extends IInputProcessor
{
	public void registerShip(IShip ship);

	public void registerFireButton(IFireButton button);
}
