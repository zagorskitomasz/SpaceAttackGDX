package spaceattack.game.ai.shooters;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.weapons.IWeaponController;

public abstract class AbstractShooter implements ShooterAI
{
	protected RadarVisible playerShip;
	protected IEnemyShip owner;
	protected IWeaponController controller;

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
