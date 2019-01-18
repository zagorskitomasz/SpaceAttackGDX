package com.zagorskidev.spaceattack.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zagorskidev.spaceattack.SpaceAttackGDX;

import spaceattack.consts.Sizes;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Space Attack";
		config.useGL30 = true;
		config.height = Sizes.GAME_HEIGHT;
		config.width = Sizes.GAME_WIDTH;

		new LwjglApplication(new SpaceAttackGDX(), config);
	}
}
