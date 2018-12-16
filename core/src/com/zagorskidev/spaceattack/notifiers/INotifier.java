package com.zagorskidev.spaceattack.notifiers;

public interface INotifier<T>
{
	public void registerObserver(Observer<T> observer);

	public void unregisterObserver(Observer<T> observer);
}
