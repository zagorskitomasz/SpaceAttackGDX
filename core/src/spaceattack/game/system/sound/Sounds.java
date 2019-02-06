package spaceattack.game.system.sound;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.ResourceNotLoadedException;

public enum Sounds
{
	// shots
	RED_LASER("sound/redShot.mp3"), //
	TURBO_LASER("sound/greenShot.mp3"), //

	// explosions
	SMALL_SHIP_EXPLOSION("sound/exShip.mp3"), //
	BOSS_EXPLOSION("sound/exBoss.mp3");

	private String path;
	private ISound sound;

	private static boolean isTest;

	Sounds(String path)
	{
		this.path = path;
	}

	public static void load()
	{
		for (Sounds sound : values())
			sound.sound = Factories.getSoundFactory().create(sound.path);
	}

	public void play()
	{
		if (sound == null && !isTest)
			throw new ResourceNotLoadedException(name());

		sound.play();
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
}
