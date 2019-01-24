package spaceattack.game.ships.enemy;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ships.IShip;
import spaceattack.game.weapons.IWeaponController;

public interface IEnemyShip extends IShip
{
	public void setPlayerShip(RadarVisible playerShip);

	public void setMover(MoverAI mover);

	public void setShooter(ShooterAI shooter);

	public IWeaponController getWeaponController();

	public boolean isMoving();

	public void setWeaponController(IWeaponController controller);
}
