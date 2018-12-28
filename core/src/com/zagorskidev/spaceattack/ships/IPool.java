package com.zagorskidev.spaceattack.ships;

public interface IPool
{
	public boolean take(float energyCost);

	public float getAmount();

	public float getMaxAmount();

	public void setLevel();

	public void update();
}
