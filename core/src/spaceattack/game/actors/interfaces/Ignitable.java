package spaceattack.game.actors.interfaces;

public interface Ignitable extends Vulnerable
{
	public void ignite(float fireDmg,long fireDuration);
}
