package spaceattack.game.weapons.multiRedLaser;

import spaceattack.game.factories.Factories;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.redLaser.RedLaser;

public abstract class MultiShotRedLaser extends RedLaser
{
	protected IVectorFactory vectors;
	
	MultiShotRedLaser()
	{
		super();
		vectors = Factories.getVectorFactory();
	}

	@Override
	public boolean use()
	{
		if (!frameController.check())
			return false;

		if (!controller.takeEnergy(energyCost))
			return false;

		IVector centralPosition = controller.getPrimaryWeaponUsePlacement();
		
		launchMissiles(centralPosition);
		return true;
	}

	protected abstract void launchMissiles(IVector centralPosition);
}
