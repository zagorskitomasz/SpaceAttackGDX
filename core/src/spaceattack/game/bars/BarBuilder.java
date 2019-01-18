package spaceattack.game.bars;

import spaceattack.game.factories.Factories;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.utils.IUtils;

public enum BarBuilder
{
	INSTANCE;

	public Bar experienceBar(IPool pool)
	{
		ExperienceBar bar = new ExperienceBar(pool);
		AbstractBarFiller filler = new ExperienceBarFiller(pool, Factories.getUtilsFactory().create());
		bar.setActor(Factories.getActorFactory().create(bar));
		bar.setFiller(filler);

		return bar;
	}

	public Bar hpEnergyBar(IPool hpPool,IPool energyPool)
	{
		IUtils utils = Factories.getUtilsFactory().create();
		HpEnergyBar bar = new HpEnergyBar(energyPool, hpPool);
		HpBarFiller hpFiller = new HpBarFiller(hpPool, utils);
		EnergyBarFiller energyFiller = new EnergyBarFiller(energyPool, utils);
		bar.setActor(Factories.getActorFactory().create(bar));
		bar.setFillers(hpFiller, energyFiller);

		return bar;
	}
}
