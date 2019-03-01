package spaceattack.game.input;

import java.util.HashSet;
import java.util.Set;

import spaceattack.consts.Sizes;
import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;

public class MissionInputHandler implements IGameplayInput
{
	private IShip ship;
	private Set<IFireButton> buttons;
	private IUtils utils;

	public MissionInputHandler()
	{
		buttons = new HashSet<>();
	}
	
	@Override
	public void setUtils(IUtils utils)
	{
		this.utils = utils;
	}

	@Override
	public void registerShip(IShip ship)
	{
		this.ship = ship;
		IPool pool = ship.getEnergyPool();

		for (IFireButton button : buttons)
			button.setEnergyPool(pool);
	}

	@Override
	public void registerFireButton(IFireButton button)
	{
		buttons.add(button);
	}

	@Override
	public boolean touchDown(int screenX,int screenY,int pointer,int button)
	{
		for (IFireButton fireButton : buttons)
		{
			fireButton.touchDown(screenX, screenY);

		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX,int screenY,int pointer,int button)
	{
		for (IFireButton fireButton : buttons)
		{
			if (fireButton.touchUp(screenX, screenY))
				return true;
		}

		if (ship != null)
			ship.setDestination(Factories.getVectorFactory().create(screenX, Sizes.GAME_HEIGHT - screenY));

		return true;
	}

	@Override
	public IVector getTouch() 
	{
		return utils.getTouch();
	}
}
