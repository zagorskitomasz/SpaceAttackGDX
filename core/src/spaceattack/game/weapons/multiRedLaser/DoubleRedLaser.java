package spaceattack.game.weapons.multiRedLaser;

import spaceattack.consts.Sizes;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.missiles.Missile;

public class DoubleRedLaser extends MultiShotRedLaser
{	
	DoubleRedLaser()
	{
		super();
	}

	@Override
	protected void launchMissiles(IVector centralPosition) 
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
