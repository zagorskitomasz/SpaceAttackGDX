package com.zagorskidev.spaceattack.ships;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.system.FrameController;

public class Pool extends AbstractPool
{
	public static final Integer UPDATES_PER_SECOND = 10;

	private float baseAmount;
	private float increasePerLevel;
	private float baseRegen;
	private float regenPerLevel;

	private float regenPerSecond;

	private boolean destroyed;

	private Lock lock;
	private FrameController controller;

	public Pool(float baseAmount,float increasePerLevel,float baseRegen,float regenPerLevel)
	{
		super();
		lock = new ReentrantLock();
		controller = new FrameController(ExtUtilsFactory.INSTANCE.create(), UPDATES_PER_SECOND);

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
	public void destroy()
	{
		destroyed = true;
		amount = 0;
		notifyObservers();
	}
}
