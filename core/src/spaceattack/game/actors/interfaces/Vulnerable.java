package spaceattack.game.actors.interfaces;

import spaceattack.game.utils.vector.IVector;

public interface Vulnerable extends Killable
{
	public void takeDmg(float dmg);

	public float getRadius();

	public IVector getPosition();
}
