package spaceattack.consts;

import com.badlogic.gdx.Gdx;

public class Sizes
{
	public static final float GAME_WIDTH = Gdx.graphics == null ? 720 : Gdx.graphics.getWidth();
	public static final float GAME_HEIGHT = Gdx.graphics == null ? 1280 : Gdx.graphics.getHeight();
	public static final float X_FACTOR = GAME_WIDTH / 720;
	public static final float Y_FACTOR = GAME_HEIGHT / 1280;
	public static final float RADIUS_FACTOR = (X_FACTOR + Y_FACTOR) / 2;
	public static final float BUTTON_WIDTH = GAME_WIDTH * 0.6f;
	public static final float BUTTON_HEIGHT = GAME_HEIGHT * 0.08f;
	public static final float HP_ENE_BAR_WIDTH = 40 * Sizes.Y_FACTOR;
	public static final float EXP_BAR_WIDTH = 20 * Sizes.X_FACTOR;
	public static final float ENEMY_BAR_WIDTH = 8 * Sizes.Y_FACTOR;
	public static final float POWER_UP_RADIUS = 40 * RADIUS_FACTOR;
}
