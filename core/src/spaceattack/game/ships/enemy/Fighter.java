package spaceattack.game.ships.enemy;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ships.Ship;
import spaceattack.game.weapons.IWeaponController;

public class Fighter extends Ship implements IEnemyShip
{
	@Override
	public void setPlayerShip(RadarVisible playerShip)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setMover(MoverAI mover)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setShooter(ShooterAI shooter)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public IWeaponController getWeaponController()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMoving()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWeaponController(IWeaponController controller)
	{
		// TODO Auto-generated method stub

	}

}
