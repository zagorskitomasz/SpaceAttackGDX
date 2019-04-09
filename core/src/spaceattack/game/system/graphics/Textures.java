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
	M2_BACKGROUND("graphics/m2Background.jpg"),
	M3_BACKGROUND("graphics/m3Background.jpg"),
	M4_BACKGROUND("graphics/m4Background.jpg"),
	M5_BACKGROUND("graphics/m5Background.jpg"),
	M6_BACKGROUND("graphics/m6Background.jpg"),
	
	// UI
	RED_BUTTON_UP("graphics/gui/redActive.png"),
	RED_BUTTON_DOWN("graphics/gui/redPressed.png"),
	GREEN_BUTTON_UP("graphics/gui/greenActive.png"),
	GREEN_BUTTON_DOWN("graphics/gui/greenPressed.png"),
	YELLOW_BUTTON_UP("graphics/gui/yellowActive.png"),
	YELLOW_BUTTON_DOWN("graphics/gui/yellowPressed.png"),
	FIRE_BUTTON_DISABLED("graphics/gui/disabled.png"),
	COCKPIT_PANEL("graphics/gui/cockpitPanel.png"),
	JOYSTICK_BG("graphics/gui/joystickBg.png"),
	JOYSTICK_HEAD("graphics/gui/joystickHead.png"),
	
	HP_ENE_BAR("graphics/gui/barHp.png"),
	EXP_BAR("graphics/gui/barExp.png"),
	
	// ships
	PLAYER_SHIP1_F("graphics/ships/player/form1.png"),
	PLAYER_SHIP1_R("graphics/ships/player/form1R.png"),
	PLAYER_SHIP1_L("graphics/ships/player/form1L.png"),

	PLAYER_SHIP2_F("graphics/ships/player/form2.png"),
	PLAYER_SHIP2_R("graphics/ships/player/form2R.png"),
	PLAYER_SHIP2_L("graphics/ships/player/form2L.png"),

	FIGHTER1("graphics/ships/fighter/form1.png"),
	FIGHTER2("graphics/ships/fighter/form2.png"),

	CHASER1("graphics/ships/chaser/form1.png"),
	CHASER2("graphics/ships/chaser/form2.png"),

	TANK1("graphics/ships/tank/form1.png"),
	TANK2("graphics/ships/tank/form2.png"),

	BOSS1("graphics/ships/boss/form1.png"),
	BOSS2("graphics/ships/boss/form2.png"),
	
	// weapons
	RED_LASER_NS("graphics/weapons/laserNS.png"),
	RED_LASER_WE("graphics/weapons/laserWE.png"),
	RED_LASER_S1("graphics/weapons/laserS1.png"),
	RED_LASER_S2("graphics/weapons/laserS2.png"),
	TURBO_LASER("graphics/weapons/turbo.png"), 
	ROCKET_MISSILE_P("graphics/weapons/missileP.png"), 
	ROCKET_MISSILE_E("graphics/weapons/missileE.png"), 
	MINE("graphics/weapons/mine.png"), 
	
	// power ups
	HP_POWER_UP("graphics/powerups/hpPU.png"),
	ENERGY_POWER_UP("graphics/powerups/energyPU.png"), 
	ROCKET_POWER_UP("graphics/powerups/rocketPU.png"),
	MINE_POWER_UP("graphics/powerups/minePU.png"),
	ROCKET_ICON("graphics/powerups/rocketIcon.png"),
	MINE_ICON("graphics/powerups/mineIcon.png");
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
