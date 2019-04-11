package spaceattack.game.actors.interfaces;

public interface Vulnerable extends Killable, Collisionable
{
	void takeDmg(float dmg);
}
