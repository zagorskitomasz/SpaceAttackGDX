package com.zagorskidev.spaceattack.system;

import java.util.LinkedList;
import java.util.List;

import com.zagorskidev.spaceattack.consts.Experience;
import com.zagorskidev.spaceattack.notifiers.INotifier;
import com.zagorskidev.spaceattack.notifiers.IObserver;

public class GameProgress implements INotifier<GameProgress>
{
	private Integer mission;
	private Integer level;
	private Long experience;

	private transient List<IObserver<GameProgress>> observers;

	public GameProgress()
	{
		mission = 1;
		level = 1;
		experience = 0l;

		observers = new LinkedList<>();
	}

	public Integer getMission()
	{
		return mission;
	}

	public void setMission(Integer mission)
	{
		this.mission = mission;
		notifyObservers();
	}

	public Integer getLevel()
	{
		return level;
	}

	public void setLevel(Integer level)
	{
		this.level = level;
		notifyObservers();
	}

	public Long getExperience()
	{
		return experience;
	}

	public void setExperience(Long experience)
	{
		this.experience = experience;
	}

	public void addExperience(long amount)
	{
		experience += amount;

		if (experience >= Experience.nextLevelReq[level])
			setLevel(level + 1);
	}

	@Override
	public boolean equals(Object other)
	{
		if (other == null || !(other instanceof GameProgress))
			return false;

		GameProgress otherProgress = (GameProgress) other;

		return otherProgress.experience == experience && otherProgress.level == level
				&& otherProgress.mission == mission;
	}

	@Override
	public void registerObserver(IObserver<GameProgress> observer)
	{
		observers.add(observer);
	}

	@Override
	public void unregisterObserver(IObserver<GameProgress> observer)
	{
		observers.remove(observer);
	}

	public void notifyObservers()
	{
		for (IObserver<GameProgress> observer : observers)
			observer.notify(this);
	}

	@Override
	public GameProgress clone()
	{
		GameProgress newProgress = new GameProgress();

		newProgress.experience = this.experience;
		newProgress.level = this.level;
		newProgress.mission = this.mission;

		return newProgress;
	}
}
