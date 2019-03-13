package spaceattack.game.system.graphics;

import spaceattack.game.factories.Factories;

public enum Animations
{
	//@formatter:off
	
	// explosions
	FIGHTER_EX("graphics/animations/fighterEx.atlas",false,10),
	BOSS_EX("graphics/animations/bossEx.atlas",false,10),
	MISSILE_EX("graphics/animations/missileEx.atlas",false,10),
	TANK_EX("graphics/animations/tankEx.atlas",false,10),
	FIRE("graphics/animations/fire.atlas",true,10);
	//@formatter:on

	private String path;
	private boolean loop;
	private float fps;

	private static boolean isTest;

	Animations(String path,boolean loop,float fps)
	{
		this.path = path;
		this.loop = loop;
		this.fps = fps;
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
			return Factories.getAnimationFactory().createLooping(path, fps);

		return Factories.getAnimationFactory().create(path, fps);
	}
}
