package spaceattack.game.system.graphics;

import spaceattack.game.factories.Factories;

public enum Animations
{
	//@formatter:off
	
	// explosions
	FIGHTER_EX("graphics/animations/fighterEx.atlas",false),
	BOSS_EX("graphics/animations/bossEx.atlas",false),
	FIRE("graphics/animations/fire.atlas",true);
	//@formatter:on

	private String path;
	private boolean loop;

	private static boolean isTest;

	Animations(String path,boolean loop)
	{
		this.path = path;
		this.loop = loop;
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

		if (loop)
			return Factories.getAnimationFactory().createLooping(path);

		return Factories.getAnimationFactory().create(path);
	}
}
