package com.zagorskidev.spaceattack.ships;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.zagorskidev.spaceattack.notifiers.IObserver;
import com.zagorskidev.spaceattack.system.FrameController;

public class Pool implements IPool
{
	public static final Integer UPDATES_PER_SECOND = 10;

	private float baseAmount;
	private float increasePerLevel;
	private float baseRegen;
	private float regenPerLevel;

	private float maxAmount;
	private float amount;
	private float regenPerSecond;

	private boolean destroyed;
	private Set<IObserver<Float>> observers;

	private Lock lock;
	private FrameController controller;

	public Pool(float baseAmount,float increasePerLevel,float baseRegen,float regenPerLevel)
	{
		lock = new ReentrantLock();
		controller = new FrameController(UPDATES_PER_SECOND);
		observers = new HashSet<>();

		this.baseAmount = baseAmount;
		this.increasePerLevel = increasePerLevel;
		this.baseRegen = baseRegen;
		this.regenPerLevel = regenPerLevel;

		setLevel(1);
	}

	@Override
	public boolean take(float amountTaken)
	{
		try
		{
			lock.lock();
			return doTake(amountTaken);
		}
		finally
		{
			lock.unlock();
		}
	}

	boolean doTake(float amountTaken)
	{
		if (amountTaken <= amount)
		{
			amount -= amountTaken;
			notifyObservers();

			return true;
		}
		return false;
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

	@Override
	public void setLevel(int level)
	{
		try
		{
			lock.lock();
			doSetLevel(level);
		}
		finally
		{
			lock.unlock();
		}
	}

	void doSetLevel(int level)
	{
		maxAmount = baseAmount + (level - 1) * increasePerLevel;
		regenPerSecond = baseRegen + (level - 1) * regenPerLevel;

		amount = maxAmount;

		notifyObservers();
	}

	@Override
	public void update()
	{
		try
		{
			lock.lock();
			doUpdate();
		}
		finally
		{
			lock.unlock();
		}
	}

	void doUpdate()
	{
		if (controller.check() && !destroyed)
		{
			amount += regenPerSecond / UPDATES_PER_SECOND;

			if (amount > maxAmount)
				amount = maxAmount;

			notifyObservers();
		}
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

	private void notifyObservers()
	{
		if (observers == null || observers.size() <= 0)
			return;

		float poolPercent = amount / maxAmount;

		for (IObserver<Float> observer : observers)
			observer.notify(poolPercent);
	}

	@Override
	public void destroy()
	{
		destroyed = true;
		amount = 0;
	}
}
