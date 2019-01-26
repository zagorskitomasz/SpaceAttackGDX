package spaceattack.game.weapons;

public interface IWeapon
{
	public void setLevel(int level);

	public boolean use();

	public float getWeaponsMovementFactor();

	public float getEnergyCost();

	public float getCollisionRadius();
}
