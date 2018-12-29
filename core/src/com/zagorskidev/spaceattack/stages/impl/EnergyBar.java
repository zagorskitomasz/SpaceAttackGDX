package com.zagorskidev.spaceattack.stages.impl;

import com.zagorskidev.spaceattack.notifiers.INotifier;

public class EnergyBar extends Bar
{
	public EnergyBar(INotifier<Float> notifier)
	{
		super(notifier);
	}
}
