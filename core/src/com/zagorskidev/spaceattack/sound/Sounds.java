package com.zagorskidev.spaceattack.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum Sounds
{
	RED_LASER("sound/redShot.mp3");

	private String path;
	private Sound sound;

	private static boolean isTest;

	Sounds(String path)
	{
		this.path = path;
	}

	public static void load()
	{
		for (Sounds sound : values())
			sound.sound = Gdx.audio.newSound(Gdx.files.internal(sound.path));
	}

	public void play()
	{
		if (sound == null && !isTest)
			throw new SoundNotLoadedException(name());

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
