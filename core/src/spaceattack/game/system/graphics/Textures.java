package spaceattack.game.system.graphics;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.ResourceNotLoadedException;

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
	RED_BUTTON_UP("graphics/gui/redActive.png"),
	RED_BUTTON_DOWN("graphics/gui/redPressed.png"),
	GREEN_BUTTON_UP("graphics/gui/greenActive.png"),
	GREEN_BUTTON_DOWN("graphics/gui/greenPressed.png"),
	YELLOW_BUTTON_UP("graphics/gui/yellowActive.png"),
	YELLOW_BUTTON_DOWN("graphics/gui/yellowPressed.png"),
	FIRE_BUTTON_DISABLED("graphics/gui/disabled.png"),
	
	HP_ENE_BAR("graphics/gui/barHp.png"),
	EXP_BAR("graphics/gui/barExp.png"),
	
	// ships
	PLAYER_SHIP_F("graphics/ships/player/form1.png"),
	PLAYER_SHIP_R("graphics/ships/player/form1R.png"),
	PLAYER_SHIP_L("graphics/ships/player/form1L.png"),
	
	// weapons
	RED_LASER("graphics/weapons/laserNS.png");
	//@formatter:on

	private String path;
	private ITexture texture;

	private static boolean isTest;

	Textures(String path)
	{
		this.path = path;
	}

	public static void load()
	{
		for (Textures texture : values())
			texture.texture = Factories.getTextureFactory().create(texture.path);
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

	public ITexture getTexture()
	{
		if (texture == null && !isTest)
			throw new ResourceNotLoadedException(name());

		return texture;
	}
}
