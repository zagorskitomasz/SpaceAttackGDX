package spaceattack.game.weapons.targetedRedLaser;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.laser.Laser;
import spaceattack.game.weapons.missiles.Missile;

public class TargetedRedLaser extends Laser
{
	private float xDirection;
	private float yDirection;
	
	private IVector movement;
	private IVector shotPosition;
	private Textures texture;
	
	TargetedRedLaser()
	{
		// do nothing
	}

	@Override
	public void setLevel(int level)
	{
		dmg = Consts.Weapons.RED_LASER_BASE_DMG + (level - 1) * Consts.Weapons.RED_LASER_DMG_PER_LEVEL;
		speed = Consts.Weapons.RED_LASER_BASE_SPEED + (level - 1) * Consts.Weapons.RED_LASER_SPEED_PER_LEVEL;
		energyCost = 0;
	}

	@Override
	protected Missile buildMissile()
	{
		Missile missile = new Missile();
		
		prepareDirection();

		missile.setActor(Factories.getActorFactory().create(missile));
		missile.setTexture(texture.getTexture());
		missile.setDmg(dmg);
		missile.setSpeed(speed);
		missile.setAcceleration(0);
		missile.setMovement(movement);
		missile.setPosition(shotPosition);
		missile.setRadius(Consts.Weapons.LASER_RADIUS);
		missile.setSound(Sounds.RED_LASER);
		missile.setPlayersAttack(controller.isPlayer());

		return missile;
	}

	private void prepareDirection() 
	{
		movement = controller.getTargetedWeaponMovement();
		xDirection = movement.getX();
		yDirection = movement.getY();
		
		setShotPosition();
		setTexture();
	}

	private void setShotPosition() 
	{
		IVector shipCoords = controller.getPrimaryWeaponUsePlacement();
		
		float x;
		float y;
		
		if(xDirection < 0)
			x = shipCoords.getX() - controller.getShipsWidth() * 0.7f;
		else if(xDirection > 0)
			x = shipCoords.getX() + controller.getShipsWidth() * 0.7f;
		else
			x = shipCoords.getX();
		
		if(yDirection < 0)
			y = shipCoords.getY() - controller.getShipsHeight() * 0.7f;
		else if(yDirection > 0)
			y = shipCoords.getY() + controller.getShipsHeight() * 0.7f;
		else
			y = shipCoords.getY();
			
		shotPosition = Factories.getVectorFactory().create(x, y);
	}

	private void setTexture() 
	{
		if(xDirection < 0 && yDirection < -0 || xDirection > 0 && yDirection > 0)
			texture = Textures.RED_LASER_S2;
		else if(xDirection < 0 && yDirection > 0 || xDirection > 0 && yDirection < 0)
			texture = Textures.RED_LASER_S1;
		else if(xDirection == 0f)
			texture = Textures.RED_LASER_NS;
		else // yDirection == 0f
			texture = Textures.RED_LASER_WE;
	}
	
	Textures getTexture()
	{
		return texture;
	}
	
	@Override
	public float getWeaponsMovementFactor()
	{
		return 0f;
	}
}
