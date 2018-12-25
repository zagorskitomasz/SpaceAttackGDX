package com.zagorskidev.spaceattack.sound;

public class SoundNotLoadedException extends RuntimeException
{
	public SoundNotLoadedException(String name)
	{
		super("Sound: " + name + " not loaded. Use Sounds.load() before playing any of them.");
	}

	private static final long serialVersionUID = -7631557989327865692L;
}
