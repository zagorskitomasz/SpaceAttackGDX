package spaceattack.game.ships.pools;

import java.util.HashSet;
import java.util.Set;

import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.notifiers.IObserver;

public abstract class AbstractPool implements IPool
{
	private Set<IObserver<Float>> observers;

	protected float maxAmount;
	protected float amount;

	public AbstractPool()
	{
		observers = new HashSet<>();
	}

	@Override
	public void registerObserver(IObserver<Float> observer)
	{
		observers.add(observer);
	}

	@Override
	public void unregisterObserver(IObserver<Float> observer)
	{
		if (observers == null)
			return;

		observers.remove(observer);
	}

	protected void notifyObservers()
	{
		if (observers == null || observers.size() <= 0)
			return;

		float poolPercent = amount / maxAmount;

		for (IObserver<Float> observer : observers)
			observer.notify(poolPercent);
	}

	@Override
	public float getAmount()
	{
		return amount;
	}

	@Override
	public float getMaxAmount()
	{
		return maxAmount;
	}
}
