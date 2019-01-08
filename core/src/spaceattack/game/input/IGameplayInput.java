package spaceattack.game.input;

import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;

public interface IGameplayInput extends IInputProcessor
{
	public void registerShip(IShip ship);

	public void registerFireButton(FireButton button);
}
