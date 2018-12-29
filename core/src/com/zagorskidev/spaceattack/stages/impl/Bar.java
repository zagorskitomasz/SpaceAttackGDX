package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.notifiers.INotifier;
import com.zagorskidev.spaceattack.notifiers.IObserver;

public class Bar extends Actor implements IObserver<Float>
{
	public Bar(INotifier<Float> notifier)
	{
		notifier.registerObserver(this);
	}

	@Override
	public void notify(Float state)
	{
		// TODO
	}
}
