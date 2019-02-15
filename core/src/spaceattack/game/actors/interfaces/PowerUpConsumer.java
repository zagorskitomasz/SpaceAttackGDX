package spaceattack.game.actors.interfaces;

import spaceattack.game.powerup.IPowerUp;

public interface PowerUpConsumer
{
	public void consume(IPowerUp powerUp);

	public float getX();

	public float getY();

	public float getRadius();
}
