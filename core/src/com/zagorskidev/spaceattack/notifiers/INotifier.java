package com.zagorskidev.spaceattack.notifiers;

public interface INotifier<T>
{
	public void registerObserver(IObserver<T> observer);

	public void unregisterObserver(IObserver<T> observer);
}
