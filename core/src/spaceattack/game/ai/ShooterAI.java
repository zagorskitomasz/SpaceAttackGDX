package spaceattack.game.ai;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.ships.enemy.IEnemyShip;

public interface ShooterAI
{
	public ShooterType getType();

	public void setPlayerShip(RadarVisible playerShip);

	public void setOwner(IEnemyShip fighter);

	public PossibleAttacks checkShot();
}
