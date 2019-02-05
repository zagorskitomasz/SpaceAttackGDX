package spaceattack.game.system.graphics;

import spaceattack.game.factories.Factories;

public enum Animations
{
	//@formatter:off
	
	// explosions
	FIGHTER_EX("graphics/animations/fighterEx.atlas"),
	BOSS_EX("graphics/animations/bossEx.atlas");
	//@formatter:on

	private String path;

	private static boolean isTest;

	Animations(String path)
	{
		this.path = path;
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

	public IAnimation getAnimation()
	{
		if (isTest)
			return null;

		return Factories.getAnimationFactory().create(path);
	}
}
