package spaceattack.game.actors.interfaces;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;

public interface Killable
{
	public static final float MARGIN = 100;

	public void setToKill();

	public boolean isToKill();

	public default void disappearIfNeeded()
	{
		if(isOutOfScreen())
			setToKill();
	}

	public default boolean isOutOfScreen() 
	{
		return getX() < 0 - MARGIN || getX() > Sizes.GAME_WIDTH + MARGIN || getY() < 0 - MARGIN || getY() > Sizes.GAME_HEIGHT + MARGIN + Consts.AI.FRONT_CHASER_DISTANCE;
	}

	public float getX();
	public float getY();
}