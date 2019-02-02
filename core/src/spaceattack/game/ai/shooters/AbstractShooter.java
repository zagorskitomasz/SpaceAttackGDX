package spaceattack.game.ai.shooters;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.IWeaponController;

public abstract class AbstractShooter implements ShooterAI
{
	protected RadarVisible playerShip;
	protected IEnemyShip owner;
	protected IWeaponController controller;
	protected IVectorFactory vectors;

	public AbstractShooter()
	{
		vectors = Factories.getVectorFactory();
	}

	@Override
	public void setPlayerShip(RadarVisible playerShip)
	{
		this.playerShip = playerShip;
	}

	@Override
	public void setOwner(IEnemyShip owner)
	{
		this.owner = owner;
		controller = owner.getWeaponController();
	}
}
