package spaceattack.game.weapons.tripleGreenLaser;

import spaceattack.consts.Sizes;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.greenLaser.GreenLaser;
import spaceattack.game.weapons.missiles.Missile;

public class TripleGreenLaser extends GreenLaser
{
	private IVectorFactory vectors;
	
	TripleGreenLaser()
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

		IVector centralPosition = controller.getSecondaryWeaponUsePlacement();
		
		launchMissiles(centralPosition);
		return true;
	}

	@Override
	public void setLevel(int level)
	{
		super.setLevel(level);
		energyCost *= 2;
	}

	private void launchMissiles(IVector centralPosition) 
	{
		Missile left = buildMissile();
		left.setSound(null);
		left.setPosition(vectors.create(centralPosition.getX() - Sizes.MULTI_MISSILES_X_DISTANCE, centralPosition.getY()));
		
		Missile right = buildMissile();
		right.setSound(null);
		right.setPosition(vectors.create(centralPosition.getX() + Sizes.MULTI_MISSILES_X_DISTANCE, centralPosition.getY()));
		
		Missile central = buildMissile();
		central.setPosition(vectors.create(centralPosition.getX(), centralPosition.getY() - Sizes.MULTI_MISSILES_Y_DISTANCE));
		
		launcher.launch(left);
		launcher.launch(right);
		launcher.launch(central);
	}

	@Override
	protected Missile buildMissile()
	{
		Missile missile = new Missile();

		missile.setActor(Factories.getActorFactory().create(missile));
		missile.setTexture(Textures.TURBO_LASER.getTexture());
		missile.setDmg(dmg);
		missile.setSpeed(speed);
		missile.setAcceleration(0);
		missile.setMovement(controller.getWeaponMovement());
		missile.setPosition(controller.getSecondaryWeaponUsePlacement());
		missile.setSound(Sounds.TURBO_LASER);
		missile.setPlayersAttack(controller.isPlayer());

		return missile;
	}
	
	@Override
	public float getWeaponsMovementFactor()
	{
		return 0f;
	}
}
