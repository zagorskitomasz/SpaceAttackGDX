package spaceattack.game.weapons.doubleRedLaser;

import spaceattack.consts.Sizes;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.missiles.Missile;
import spaceattack.game.weapons.redLaser.RedLaser;

public class DoubleRedLaser extends RedLaser
{
	private IVectorFactory vectors;
	
	DoubleRedLaser()
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

	private void launchMissiles(IVector centralPosition) 
	{
		Missile left = buildMissile();
		left.setSound(null);
		left.setPosition(vectors.create(centralPosition.getX() - Sizes.MULTI_MISSILES_X_DISTANCE / 2, centralPosition.getY()));
		
		Missile right = buildMissile();
		right.setPosition(vectors.create(centralPosition.getX() + Sizes.MULTI_MISSILES_X_DISTANCE / 2, centralPosition.getY()));
		
		launcher.launch(left);
		launcher.launch(right);
	}
}
