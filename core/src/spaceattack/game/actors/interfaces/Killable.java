package spaceattack.game.actors.interfaces;

import spaceattack.consts.Sizes;

public interface Killable
{
	public static final float DOWN_MARGIN = 100;
	public static final float MARGIN = DOWN_MARGIN + 380 * Sizes.Y_FACTOR;

	public void setToKill();

	public boolean isToKill();

	public default void disappearIfNeeded()
	{
		if(isOutOfScreen())
		{
			setToKill();
		}
	}

	public default boolean isOutOfScreen() 
	{
		return getX() < 0 - MARGIN || getX() > Sizes.GAME_WIDTH + MARGIN || getY() < 0 - DOWN_MARGIN || getY() > Sizes.GAME_HEIGHT + MARGIN;
	}

	public float getX();
	public float getY();
}