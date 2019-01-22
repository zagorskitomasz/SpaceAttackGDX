package spaceattack.game.ships;

import java.util.Set;

import spaceattack.game.GameProgress;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.actors.interfaces.Vulnerable;
import spaceattack.game.engines.IEngine;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.notifiers.IObserver;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.IWeapon;

public interface IShip extends IObserver<GameProgress>,Vulnerable,IGameActor,RadarVisible
{
	public enum Turn
	{
		FRONT,LEFT,RIGHT;
	}

	public void setDestination(IVector iVector);

	public float getX();

	public float getY();

	public void setX(float x);

	public void setY(float y);

	public void setShipEngine(IEngine engine);

	public void addWeapon(IWeapon weapon);

	public Set<IWeapon> getWeapons();

	public void setLevel(int level);

	public float getHeight();

	public float getWidth();

	public boolean takeEnergy(float energyCost);

	public void setEnergyPool(IPool pool);

	public IPool getEnergyPool();

	public void setTexture(ITexture texture);
}
