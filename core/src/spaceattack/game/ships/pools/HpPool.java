package spaceattack.game.ships.pools;

import java.util.function.Supplier;

public class HpPool extends Pool
{
	private Supplier<Boolean> immunityChecker;
	
	public HpPool(float baseAmount,float increasePerLevel,float baseRegen,float regenPerLevel)
	{
		super(baseAmount, increasePerLevel, baseRegen, regenPerLevel);
	}
	
	public void setImmunityChecker(Supplier<Boolean> supplier)
	{
		immunityChecker = supplier;
	}

	@Override
	boolean doTake(float amountTaken)
	{
		if(immunityChecker != null && immunityChecker.get())
			return true;
		
		boolean result;

		if (amountTaken < amount)
		{
			amount -= amountTaken;
			result = true;
		}
		else
		{
			destroy();
			result = false;
		}
		notifyObservers();
		return result;
	}
}
