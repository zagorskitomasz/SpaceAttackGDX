package com.zagorskidev.spaceattack.notifiers;

public interface IObserver<T>
{
	public void notify(T state);
}
