package com.zagorskidev.spaceattack.notifiers;

public interface Observer<T>
{
	public void notify(T state);
}
