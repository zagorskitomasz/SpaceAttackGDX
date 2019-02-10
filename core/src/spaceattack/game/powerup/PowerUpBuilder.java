package spaceattack.game.powerup;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.Textures;

public enum PowerUpBuilder
{
	INSTANCE;

	public IPowerUp hp(IPool hpPool)
	{
		PoolIncreaser increaser = new PoolIncreaser();
		increaser.setPool(hpPool);
		increaser.setIncreasePercent(Consts.AI.POOL_INCREASE_PERCENT);
		increaser.setTexture(Textures.HP_POWER_UP);
		increaser.setActor(Factories.getActorFactory().create(increaser));

		return increaser;
	}

	public IPowerUp energy(IPool energyPool)
	{
		PoolIncreaser increaser = new PoolIncreaser();
		increaser.setPool(energyPool);
		increaser.setIncreasePercent(Consts.AI.POOL_INCREASE_PERCENT);
		increaser.setTexture(Textures.ENERGY_POWER_UP);
		increaser.setActor(Factories.getActorFactory().create(increaser));

		return increaser;
	}
}
