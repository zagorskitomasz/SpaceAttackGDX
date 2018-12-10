package com.zagorskidev.spaceattack.consts;

import com.badlogic.gdx.Gdx;

public class Consts
{
	public static final float GAME_WIDTH = Gdx.graphics != null ? Gdx.graphics.getWidth() : 400;
	public static final float GAME_HEIGHT = Gdx.graphics != null ? Gdx.graphics.getHeight() : 750;

	public static final float BUTTON_WIDTH = GAME_WIDTH * 0.6f;
	public static final float BUTTON_HEIGHT = GAME_HEIGHT * 0.09f;

	public static final String DEFAULT = "default";
	public static final String RED_BTN = "red-btn";
	public static final int MISSIONS_IN_ACT = 3;
	public static final int ACTS_NUMBER = 3;
}
