package com.zagorskidev.spaceattack.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zagorskidev.spaceattack.SpaceAttackGDX;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Space Attack";
		config.useGL30 = true;
		config.height = 700;
		config.width = 400;

		new LwjglApplication(new SpaceAttackGDX(), config);
	}
}
