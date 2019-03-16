package spaceattack.game.ships.pools;

public class HpPool extends Pool
{
	public HpPool(float baseAmount,float increasePerLevel,float baseRegen,float regenPerLevel)
	{
		super(baseAmount, increasePerLevel, baseRegen, regenPerLevel);
	}

	@Override
	boolean doTake(float amountTaken)
	{
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
