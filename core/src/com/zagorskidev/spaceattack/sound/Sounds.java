package com.zagorskidev.spaceattack.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum Sounds
{
	RED_LASER("sound/redShot.mp3");

	private String path;
	private Sound sound;

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
		if (sound == null)
			throw new SoundNotLoadedException(name());

		sound.play();
	}
}
