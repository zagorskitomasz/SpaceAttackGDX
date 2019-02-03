package spaceattack.game.ships;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import spaceattack.game.GameProgress;
import spaceattack.game.actors.DrawableActor;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.engines.IEngine;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.MissilesLauncher;

public abstract class Ship extends DrawableActor implements IShip
{
	protected ITexture currentTexture;

	protected IEngine engine;
	protected Set<IWeapon> weapons;

	private IPool energyPool;
	private IPool hpPool;

	private boolean isToKill;

	private MissilesLauncher launcher;
	private Launchable explosion;

	@Override
	public void setTexture(ITexture texture)
	{
		currentTexture = texture;
	}

	@Override
	public void setShipEngine(IEngine engine)
	{
		this.engine = engine;
	}

	@Override
	protected ITexture getTexture()
	{
		return currentTexture;
	}

	@Override
	public void setDestination(IVector destination)
	{
		if (engine != null)
			engine.setDestination(destination);
	}

	@Override
	public void setEnergyPool(IPool pool)
	{
		energyPool = pool;
	}

	@Override
	public void setHpPool(IPool pool)
	{
		hpPool = pool;
	}

	@Override
	public void act(float delta)
	{
		if (energyPool != null)
			energyPool.update();

		if (hpPool != null)
			hpPool.update();

		if (engine != null)
			fly();
	}

	protected void fly()
	{
		engine.fly();
	}

	@Override
	public void addWeapon(IWeapon weapon)
	{
		if (weapons == null)
			weapons = new CopyOnWriteArraySet<>();

		weapons.add(weapon);
	}

	@Override
	public Set<IWeapon> getWeapons()
	{
		return weapons;
	}

	@Override
	public void setLevel(int level)
	{
		if (engine != null)
			engine.setLevel(level);

		if (energyPool != null)
			energyPool.setLevel(level);

		if (hpPool != null)
			hpPool.setLevel(level);

		if (weapons != null)
			for (IWeapon weapon : weapons)
				weapon.setLevel(level);
	}

	@Override
	public float getHeight()
	{
		return currentTexture.getHeight();
	}

	@Override
	public float getWidth()
	{
		return currentTexture.getWidth();
	}

	@Override
	public void takeDmg(float dmg)
	{
		if (!hpPool.take(dmg))
		{
			setToKill();
			energyPool.destroy();
		}
	}

	@Override
	public float getRadius()
	{
		return (getHeight() + getWidth()) * 0.25f;
	}

	@Override
	public IVector getPosition()
	{
		return Factories.getVectorFactory().create(getX(), getY());
	}

	@Override
	public void setToKill()
	{
		isToKill = true;
		explode();
	}

	@Override
	public boolean isToKill()
	{
		return isToKill;
	}

	@Override
	public boolean takeEnergy(float energyCost)
	{
		return energyPool.take(energyCost);
	}

	@Override
	public IPool getEnergyPool()
	{
		return energyPool;
	}

	@Override
	public IPool getHpPool()
	{
		return hpPool;
	}

	@Override
	public void notify(GameProgress state)
	{
		setLevel(state.getLevel());
	}

	@Override
	public void setMissilesLauncher(MissilesLauncher launcher)
	{
		this.launcher = launcher;
	}

	@Override
	public void explode()
	{
		if (launcher == null || explosion == null)
			return;

		IActor actor = getActor();
		explosion.getActor().setPosition(actor.getX(), actor.getY());
		launcher.launch(explosion);
	}

	@Override
	public void setExplosion(Launchable explosion)
	{
		this.explosion = explosion;
	}

	@Override
	public void ignite(float fireDmg,long fireDuration)
	{
		// TODO Auto-generated method stub

	}
}
