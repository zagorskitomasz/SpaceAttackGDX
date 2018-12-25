package com.zagorskidev.spaceattack.graphics;

import com.badlogic.gdx.graphics.Texture;

public enum Textures
{
	//@formatter:off
	
	// logos
	LOGO("graphics/logo.png"),
	ACT_1_LOGO("graphics/actLogo1.png"),
	ACT_2_LOGO("graphics/actLogo2.png"),
	ACT_3_LOGO("graphics/actLogo3.png"),
	
	// backgrounds	
	MENU_BACKGROUND("graphics/menuBackground.jpg"),
	M1_BACKGROUND("graphics/m1Background.jpg"),
	
	// UI
	PF_BUTTON_UP("graphics/gui/redActive.png"),
	PF_BUTTON_DOWN("graphics/gui/redPressed.png"),
	FIRE_BUTTON_DISABLED("graphics/gui/disabled.png"),
	
	// ships
	PLAYER_SHIP_F("graphics/ships/player/form1.png"),
	PLAYER_SHIP_R("graphics/ships/player/form1R.png"),
	PLAYER_SHIP_L("graphics/ships/player/form1L.png"),
	
	// weapons
	RED_LASER("graphics/weapons/laserNS.png");
	//@formatter:on

	private String path;
	private Texture texture;

	private static boolean isTest;

	Textures(String path)
	{
		this.path = path;
	}

	public static void load()
	{
		for (Textures texture : values())
			texture.texture = new Texture(texture.path);
	}

	/**
	 * Only for JUnit test purposes! This fake method won't load any real textures.
	 * It will only suppress TextureNotLoadedException. After fake load
	 * Textures#getTexture() method will return null!
	 */
	public static void loadForTest()
	{
		isTest = true;
	}

	public Texture getTexture()
	{
		if (texture == null && !isTest)
			throw new TextureNotLoadedException(name());

		return texture;
	}
}
